package com.github.sagaflow.eventStream.event;

import lombok.Getter;

@Getter
public class SagaStepFailedEvent extends BaseEvent {

    private final Throwable cause;

    public SagaStepFailedEvent(String name, Throwable cause) {
        super(name);
        this.cause = cause;
    }

    @Override
    public String eventType() {
        return "step failed";
    }
}
