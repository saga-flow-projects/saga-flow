package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.SagaStep;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class SagaRetryManager {

    int maxRetries;

    public boolean retry(SagaStep step, SagaContext context) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                step.execute(context);
                return true;
            } catch (SagaException e) {
                log.info("Retry attempt {} failed for step: {}", attempt, step.getClass().getSimpleName());
            }
        }
        return false;
    }
}