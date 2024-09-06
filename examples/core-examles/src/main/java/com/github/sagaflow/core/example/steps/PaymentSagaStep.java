package com.github.sagaflow.core.example.steps;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaException;
import com.github.sagaflow.core.steps.AbstractSagaStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentSagaStep extends AbstractSagaStep {

    @Override
    protected void doExecute(SagaContext context) throws SagaException {
        // Simulate payment processing
        log.info("Processing payment for user: {}", context.getUserId());
        if (Math.random() > 0.5) {
            throw new SagaException("Payment failed for user: " + context.getUserId());
        }
        log.info("Payment successful.");
    }

    @Override
    protected void doRollback(SagaContext context) {
        log.info("Rolling back payment for user: {}", context.getUserId());
    }
}
