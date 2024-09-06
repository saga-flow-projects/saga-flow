package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public class ErrorHandlingTransactionManager extends TransactionManager {

    private final SagaErrorHandlingStrategy errorStrategy;

    public ErrorHandlingTransactionManager(SagaErrorHandlingStrategy errorStrategy) {
        this.errorStrategy = errorStrategy;
    }

    @Override
    public void beginTransaction(SagaContext context) {
        super.beginTransaction(context);
        // Custom error handling initialization
    }

    public void handleError(SagaContext context, SagaStep step, Exception e) throws SagaErrorNotHandledException {
        errorStrategy.handle(context, step, e);
    }
}