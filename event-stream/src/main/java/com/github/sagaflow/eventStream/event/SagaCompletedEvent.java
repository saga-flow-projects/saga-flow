package com.github.sagaflow.eventStream.event;

import com.github.sagaflow.eventStream.DomainEvent;

public class SagaCompletedEvent extends BaseEvent {

    public SagaCompletedEvent(String name) {
        super(name);
    }

    @Override
    public String eventType() {
        return "saga completed";
    }
}
