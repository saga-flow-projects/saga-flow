package com.github.sagaflow.eventStream.event;

import com.github.sagaflow.eventStream.DomainEvent;
import lombok.Getter;

import java.time.Instant;


@Getter
public abstract class BaseEvent implements DomainEvent {

    private final Instant occurredAt = Instant.now();
    private final String name;
    public BaseEvent(String name) {
        this.name = name;
    }

}
