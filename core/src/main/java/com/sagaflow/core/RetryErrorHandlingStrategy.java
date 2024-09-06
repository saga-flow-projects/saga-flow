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
    public void handle(SagaContext context, SagaStep step, Exception exception) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                step.execute(context);
                return;
            } catch (SagaException e) {
                log.debug("Retry attempt {} failed.", i + 1);
            }
        }
        log.error("Max retries reached, rolling back.");
        step.rollback(context);
    }
}