package com.github.sagaflow.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SagaTimeoutManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ScheduledFuture<?> startTimeout(SagaStep step, SagaContext context, long timeoutSeconds, AtomicBoolean isCompleted) {
        return scheduler.schedule(() -> {
            if (!isCompleted.get()) {
                log.warn("Timeout reached for step: {}, initiating action.", step.getClass().getSimpleName());
                // Here we simply log the timeout, but don't rollback directly
                context.setTimeoutExceeded(true); // Set a flag to notify the orchestrator
            }
        }, timeoutSeconds, TimeUnit.SECONDS);
    }

    public void stopScheduler() {
        scheduler.shutdown(); // Shut down the scheduler gracefully when no longer needed
    }
}