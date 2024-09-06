package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryErrorHandlingStrategy implements SagaErrorHandlingStrategy {

    private final int maxRetries;

    public RetryErrorHandlingStrategy(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public void handle(SagaContext context, SagaStep step, Exception exception) throws SagaErrorNotHandledException {
        for (int i = 0; i < maxRetries; i++) {
            try {
                step.execute(context);
                return; // Step executed successfully, no need for retries
            } catch (SagaException e) {
                log.debug("Retry attempt {} failed for step: {}", i + 1, step.getClass().getSimpleName());
                if (i == maxRetries - 1) {
                    throw new SagaErrorNotHandledException("Step execution failed after retries: " + e.getMessage(), e);
                }
            }
        }
    }
}