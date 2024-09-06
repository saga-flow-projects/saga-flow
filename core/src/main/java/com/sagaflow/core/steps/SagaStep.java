package com.sagaflow.core.steps;

import com.sagaflow.core.SagaContext;
import com.sagaflow.core.SagaException;

public interface SagaStep {
    void execute(SagaContext context) throws SagaException;
    void rollback(SagaContext context);
}