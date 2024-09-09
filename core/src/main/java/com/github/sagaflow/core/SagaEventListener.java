package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.SagaStep;

public interface SagaEventListener {
    void onStepCompleted(SagaContext context, SagaStep step);
    void onStepFailed(SagaContext context, SagaStep step, Exception exception);
    void onSagaCompleted(SagaContext context);
    void onSagaFailed(SagaContext context, Exception exception);
}