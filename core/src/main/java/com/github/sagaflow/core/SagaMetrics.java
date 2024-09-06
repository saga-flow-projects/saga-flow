package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.SagaStep;

public interface SagaMetrics {

    default void recordSuccess(SagaStep step){};

   default void recordFailure(SagaStep step){};
}