package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public class StepExecutor {

    private final TransactionManager transactionManager = new TransactionManager();
    private final RetryErrorHandlingStrategy retryStrategy = new RetryErrorHandlingStrategy(3); // Added retry strategy with max 3 retries

    public void executeStep(SagaStep step, SagaContext context) throws SagaException {
        transactionManager.beginTransaction(context);
        try {
            retryStrategy.handle(context, step, null); // Retry strategy handles retries
        } catch (Exception e) {
            if (transactionManager instanceof ErrorHandlingTransactionManager) {
                try {
                    ((ErrorHandlingTransactionManager) transactionManager).handleError(context, step, e);
                } catch (SagaErrorNotHandledException ex) {
                    throw new SagaException("Error handling failed after retries: " + ex.getMessage());
                }
            } else {
                throw new SagaException("Step execution failed: " + e.getMessage());
            }
        } finally {
            transactionManager.endTransaction(context);
        }
    }
}