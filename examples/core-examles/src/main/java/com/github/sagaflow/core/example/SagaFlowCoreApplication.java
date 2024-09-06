package com.github.sagaflow.core.example;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaOrchestrator;
import com.github.sagaflow.core.SagaResult;
import com.github.sagaflow.core.example.steps.PaymentCompensationStep;
import com.github.sagaflow.core.example.steps.PaymentSagaStep;

public class SagaFlowCoreApplication {

    public static void main(String[] args) {
        SagaOrchestrator orchestrator = new SagaOrchestrator();
        SagaContext context = new SagaContext();
        context.setTransactionId("TX123456");
        context.setUserId("User001");

        // Add execution steps
        orchestrator.addStep(new PaymentSagaStep());

        // Add compensation steps (for rollback)
        orchestrator.addCompensationStep(new PaymentCompensationStep());

        // Execute the saga
        SagaResult result = orchestrator.executeSaga(context);
        System.out.println("Saga Result: " + result.getMessage());
    }
}