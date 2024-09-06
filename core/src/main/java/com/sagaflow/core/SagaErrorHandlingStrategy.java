package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public interface SagaErrorHandlingStrategy {
    void handle(SagaContext context, SagaStep step, Exception exception);
}