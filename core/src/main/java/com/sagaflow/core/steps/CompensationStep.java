package com.sagaflow.core.steps;

import com.sagaflow.core.CompensationContext;
import com.sagaflow.core.SagaContext;

public interface CompensationStep {
    void compensate(SagaContext context, CompensationContext compensationContext);
}