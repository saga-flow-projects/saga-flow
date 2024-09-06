package com.github.sagaflow.core.steps;

import com.github.sagaflow.core.CompensationContext;
import com.github.sagaflow.core.SagaContext;

public interface CompensationStep {
    void compensate(SagaContext context, CompensationContext compensationContext);
}