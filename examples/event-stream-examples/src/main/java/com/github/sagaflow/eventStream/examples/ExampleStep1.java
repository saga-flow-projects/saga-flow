package com.github.sagaflow.eventStream.examples;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaException;
import com.github.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleStep1 implements SagaStep {
    @Override
    public void execute(SagaContext context) throws SagaException {
        log.info("Executing step: {}", this.getClass().getSimpleName());
    }

    @Override
    public void rollback(SagaContext context) {
        log.info("Rolling back step: {}", this.getClass().getSimpleName());
    }
}
