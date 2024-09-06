package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

public interface SagaMetrics {

    default void recordSuccess(SagaStep step){};

   default void recordFailure(SagaStep step){};
}