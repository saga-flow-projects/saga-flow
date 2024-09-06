package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.SagaStep;

public interface SagaErrorHandlingStrategy {
    void handle(SagaContext context, SagaStep step, Exception exception) throws SagaErrorNotHandledException;
}