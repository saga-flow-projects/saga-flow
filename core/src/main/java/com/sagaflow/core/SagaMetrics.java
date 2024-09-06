package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public interface SagaMetrics {

    void recordSuccess(SagaStep step);

    void recordFailure(SagaStep step);
}