package com.github.sagaflow.core.example.steps;

import com.github.sagaflow.core.CompensationContext;
import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.steps.CompensationStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentCompensationStep implements CompensationStep {

    @Override
    public void compensate(SagaContext context, CompensationContext compensationContext) {
        log.info("Compensating payment for user: " + context.getUserId() + " due to: " + compensationContext.getReason());
        // Logic to reverse payment (e.g., refund the amount)
    }
}
