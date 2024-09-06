package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SagaRetryManager {

    public boolean retry(SagaStep step, SagaContext context, int maxRetries) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                step.execute(context);
                return true;
            } catch (SagaException e) {
                log.debug("Retry attempt {} failed for step: {}", attempt, step.getClass().getSimpleName());
            }
        }
        return false;
    }
}