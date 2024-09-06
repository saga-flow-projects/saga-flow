package com.github.sagaflow.core.steps;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaException;

public interface SagaStep {
    void execute(SagaContext context) throws SagaException;
    void rollback(SagaContext context);
}