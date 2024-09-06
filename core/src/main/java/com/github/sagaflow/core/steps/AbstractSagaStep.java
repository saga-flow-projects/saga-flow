package com.github.sagaflow.core.steps;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSagaStep implements SagaStep {

    @Override
    public void execute(SagaContext context) throws SagaException {
        log.info("Executing step: {}", this.getClass().getSimpleName());
        doExecute(context);
    }

    @Override
    public void rollback(SagaContext context) {
        log.info("Rolling back step: {}", this.getClass().getSimpleName());
        doRollback(context);
    }

    protected abstract void doExecute(SagaContext context) throws SagaException;
    protected abstract void doRollback(SagaContext context);
}