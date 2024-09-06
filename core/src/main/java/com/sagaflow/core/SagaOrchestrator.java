package com.sagaflow.core;

import java.util.ArrayList;
import java.util.List;

public class SagaOrchestrator {

    private final List<SagaStep> steps = new ArrayList<>();

    public void addStep(SagaStep step) {
        steps.add(step);
    }

    public void executeSaga() {
        try {
            for (SagaStep step : steps) {
                step.execute();
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            rollbackSaga();
        }
    }

    private void rollbackSaga() {
        System.out.println("Rolling back the saga...");
        for (SagaStep step : steps) {
            step.rollback();
        }
    }
}
