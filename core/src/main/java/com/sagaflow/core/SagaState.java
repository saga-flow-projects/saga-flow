package com.sagaflow.core;

import com.sagaflow.core.steps.SagaStep;

import java.util.HashMap;
import java.util.Map;

public class SagaState {

    private final Map<SagaStep, StepStatus> stepStatuses = new HashMap<>();

    public void updateStepStatus(SagaStep step, StepStatus status) {
        stepStatuses.put(step, status);
    }

    public Map<SagaStep, StepStatus> getStepStatuses() {
        return stepStatuses;
    }

    public boolean isComplete() {
        return stepStatuses.values().stream().allMatch(status -> status == StepStatus.COMPLETED);
    }
}