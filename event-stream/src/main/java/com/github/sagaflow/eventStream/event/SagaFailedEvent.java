package com.github.sagaflow.eventStream.event;

import com.github.sagaflow.eventStream.DomainEvent;
import lombok.Getter;

@Getter
public class SagaFailedEvent extends BaseEvent {

    private final Throwable cause;

    public SagaFailedEvent(String name, Throwable cause) {
        super(name);
        this.cause = cause;
    }

    @Override
    public String eventType() {
        return "saga failed";
    }
}
