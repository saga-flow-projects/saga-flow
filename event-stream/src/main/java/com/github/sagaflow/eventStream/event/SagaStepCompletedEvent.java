package com.github.sagaflow.eventStream.event;

public class SagaStepCompletedEvent extends BaseEvent {

    public SagaStepCompletedEvent(String name) {
        super(name);
    }

    @Override
    public String eventType() {
        return "step completed";
    }
}
