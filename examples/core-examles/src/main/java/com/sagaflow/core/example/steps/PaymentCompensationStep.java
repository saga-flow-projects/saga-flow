package com.sagaflow.core.example.steps;

import com.sagaflow.core.CompensationContext;
import com.sagaflow.core.SagaContext;
import com.sagaflow.core.steps.CompensationStep;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentCompensationStep implements CompensationStep {

    @Override
    public void compensate(SagaContext context, CompensationContext compensationContext) {
        log.info("Compensating payment for user: " + context.getUserId() + " due to: " + compensationContext.getReason());
        // Logic to reverse payment (e.g., refund the amount)
    }
}
