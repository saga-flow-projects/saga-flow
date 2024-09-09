package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.CompensationStep;
import com.github.sagaflow.core.steps.SagaStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;


public class SagaOrchestrator {

    private static final Logger logger = LoggerFactory.getLogger(SagaOrchestrator.class);
    private final List<SagaStep> steps = new ArrayList<>();
    private final List<CompensationStep> compensationSteps = new ArrayList<>();
    private final StepExecutor stepExecutor;
    private final SagaMetrics sagaMetrics = new SagaMetrics(){};
    private final SagaState sagaState = new SagaState();
    private final SagaTimeoutManager timeoutManager = new SagaTimeoutManager();  // Added SagaTimeoutManager
    private final List<SagaEventListener> listeners = new ArrayList<>();


    public SagaOrchestrator() {
        this.stepExecutor = new StepExecutor(
                new ErrorHandlingTransactionManager(
                        (context, step, exception) -> {
                            throw new SagaErrorNotHandledException("Error handling strategy not implemented.");
                        }
                ),
                new SagaRetryManager(3)
        );
    }

    public void addEventListener(SagaEventListener listener) {
        listeners.add(listener);
    }

    public SagaOrchestrator(SagaErrorHandlingStrategy errorHandlingStrategy) {
        TransactionManager transactionManager = new ErrorHandlingTransactionManager(errorHandlingStrategy);
        this.stepExecutor = new StepExecutor(transactionManager, new SagaRetryManager(3));
    }

    public void addStep(SagaStep step) {
        steps.add(step);
        sagaState.updateStepStatus(step, StepStatus.PENDING);
    }

    public void addCompensationStep(CompensationStep compensationStep) {
        compensationSteps.add(compensationStep);
    }

    public SagaResult executeSaga(SagaContext context) {
        logger.info("Starting saga execution...");
        CompensationContext compensationContext = new CompensationContext();
        compensationContext.setTransactionId(context.getTransactionId());

        for (int i = 0; i < steps.size(); i++) {
            SagaStep step = steps.get(i);
            AtomicBoolean isCompleted = new AtomicBoolean(false);
            ScheduledFuture<?> timeoutTask = null;
            try {
                timeoutTask = timeoutManager.startTimeout(step, context, 10, isCompleted);
                stepExecutor.executeStep(step, context);
                isCompleted.set(true);

                if (timeoutTask != null && !timeoutTask.isDone()) {
                    timeoutTask.cancel(true);
                }
                sagaMetrics.recordSuccess(step);
                notifyStepCompleted(context, step);
                sagaState.updateStepStatus(step, StepStatus.COMPLETED);
            } catch (SagaException e) {
                logger.error("Error executing step: {}, starting compensation.", step.getClass().getSimpleName(), e);
                sagaState.updateStepStatus(step, StepStatus.FAILED);
                sagaMetrics.recordFailure(step);
                notifyStepFailed(context, step, e);

                compensationContext.setReason(e.getMessage());

                if (timeoutTask != null && !timeoutTask.isDone()) {
                    timeoutTask.cancel(true);
                }

                rollbackSaga(context, compensationContext, i);
                notifySagaFailed(context, e);
                return new SagaResult(false, e.getMessage());
            } finally {
                timeoutManager.stopScheduler();
            }
        }
        notifySagaCompleted(context);
        timeoutManager.stopScheduler();
        return new SagaResult(true, "Saga executed successfully.");
    }

    private void rollbackSaga(SagaContext context, CompensationContext compensationContext, int failedStepIndex) {
        logger.info("Rolling back saga...");
        for (int i = failedStepIndex; i >= 0; i--) {
            if (i < compensationSteps.size()) {
                CompensationStep compensationStep = compensationSteps.get(i);
                compensationStep.compensate(context, compensationContext);
            }
        }
    }

    private void notifyStepCompleted(SagaContext context, SagaStep step) {
        for (SagaEventListener listener : listeners) {
            listener.onStepCompleted(context, step);
        }
    }

    private void notifyStepFailed(SagaContext context, SagaStep step, Exception exception) {
        for (SagaEventListener listener : listeners) {
            listener.onStepFailed(context, step, exception);
        }
    }

    private void notifySagaCompleted(SagaContext context) {
        for (SagaEventListener listener : listeners) {
            listener.onSagaCompleted(context);
        }
    }

    private void notifySagaFailed(SagaContext context, Exception exception) {
        for (SagaEventListener listener : listeners) {
            listener.onSagaFailed(context, exception);
        }
    }
}