package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public class StepExecutor {

    private final TransactionManager transactionManager = new TransactionManager();

    public void executeStep(SagaStep step, SagaContext context) throws SagaException {
        transactionManager.beginTransaction(context);
        step.execute(context);
        transactionManager.endTransaction(context);
    }
}