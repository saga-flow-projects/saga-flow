package com.github.sagaflow.eventStream;

public interface EventSubscriber<T extends DomainEvent> {
    void onEvent(T event);
    Class<T> subscribedToEventType();
}
