package com.sagaflow.core;

import com.sagaflow.core.steps.CompensationStep;
import com.sagaflow.core.steps.SagaStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SagaOrchestrator {

    private static final Logger logger = LoggerFactory.getLogger(SagaOrchestrator.class);
    private final List<SagaStep> steps = new ArrayList<>();
    private final List<CompensationStep> compensationSteps = new ArrayList<>();
    private final StepExecutor stepExecutor = new StepExecutor();
    private final SagaMetrics sagaMetrics = new SagaMetrics(){};


    public void addStep(SagaStep step) {
        steps.add(step);
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
            try {
                stepExecutor.executeStep(step, context); // Using StepExecutor for step execution
                sagaMetrics.recordSuccess(step); // Recording success in metrics
            } catch (SagaException e) {
                logger.error("Error executing step: {}, starting compensation.", step.getClass().getSimpleName(), e);
                sagaMetrics.recordFailure(step); // Recording failure in metrics
                compensationContext.setReason(e.getMessage());
                rollbackSaga(context, compensationContext, i);
                return new SagaResult(false, e.getMessage());
            }
        }
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
}