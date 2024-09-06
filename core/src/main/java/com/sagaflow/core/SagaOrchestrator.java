package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SagaOrchestrator {

    private static final Logger logger = LoggerFactory.getLogger(SagaOrchestrator.class);
    private final List<SagaStep> steps = new ArrayList<>();

    public void addStep(SagaStep step) {
        steps.add(step);
    }

    public SagaResult executeSaga(SagaContext context) {
        logger.info("Starting saga execution...");
        for (SagaStep step : steps) {
            try {
                step.execute(context);
            } catch (SagaException e) {
                logger.error("Error executing step: {}, rolling back.", step.getClass().getSimpleName(), e);
                rollbackSaga(context);
                return new SagaResult(false, e.getMessage());
            }
        }
        return new SagaResult(true, "Saga executed successfully.");
    }

    private void rollbackSaga(SagaContext context) {
        for (SagaStep step : steps) {
            step.rollback(context);
        }
    }
}