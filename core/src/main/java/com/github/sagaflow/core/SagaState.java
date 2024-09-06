package com.github.sagaflow.core;

import com.github.sagaflow.core.steps.SagaStep;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SagaState {

    private final Map<SagaStep, StepStatus> stepStatuses = new HashMap<>();

    public void updateStepStatus(SagaStep step, StepStatus status) {
        stepStatuses.put(step, status);
    }

    public boolean isComplete() {
        return stepStatuses.values().stream().allMatch(status -> status == StepStatus.COMPLETED);
    }
}