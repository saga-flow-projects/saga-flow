package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public class StepExecutor {

    private final TransactionManager transactionManager;
    private final SagaRetryManager retryManager;

    public StepExecutor(TransactionManager transactionManager, SagaRetryManager retryManager) {
        this.transactionManager = transactionManager;
        this.retryManager = retryManager;
    }

    public void executeStep(SagaStep step, SagaContext context) throws SagaException {
        transactionManager.beginTransaction(context);
        try {
            boolean success = retryManager.retry(step, context);
            if (!success) {
                throw new SagaException("Step execution failed after retries.");
            }
        } catch (Exception e) {
            if (transactionManager instanceof ErrorHandlingTransactionManager) {
                // Handle error using the error handling strategy
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