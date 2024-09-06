package com.sagaflow.core.example;

import com.sagaflow.core.SagaContext;
import com.sagaflow.core.SagaOrchestrator;
import com.sagaflow.core.SagaResult;
import com.sagaflow.core.example.steps.PaymentSagaStep;

public class SagaFlowCoreApplication {

    public static void main(String[] args) {
        SagaOrchestrator orchestrator = new SagaOrchestrator();
        SagaContext context = new SagaContext();
        context.setTransactionId("TX123456");
        context.setUserId("User001");

        // Add steps
        orchestrator.addStep(new PaymentSagaStep());

        // Execute the saga
        SagaResult result = orchestrator.executeSaga(context);
        System.out.println("Saga Result: " + result.getMessage());
    }
}