package com.github.sagaflow.eventStream;

import java.time.Instant;

public interface DomainEvent {
    Instant getOccurredAt();
    String eventType();
}