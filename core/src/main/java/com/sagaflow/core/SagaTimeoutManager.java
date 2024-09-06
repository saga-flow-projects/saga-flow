package com.sagaflow.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SagaTimeoutManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startTimeout(SagaStep step, SagaContext context, long timeoutSeconds) {
        scheduler.schedule(() -> {
            log.warn("Timeout reached for step: {}, initiating rollback.", step.getClass().getSimpleName());
            step.rollback(context);
        }, timeoutSeconds, TimeUnit.SECONDS);
    }
}