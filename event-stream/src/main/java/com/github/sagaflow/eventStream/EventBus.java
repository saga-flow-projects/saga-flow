package com.github.sagaflow.eventStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Class<? extends DomainEvent>, List<EventSubscriber<? extends DomainEvent>>> subscribersMap = new HashMap<>();

    // Register subscribers for specific event types
    public <T extends DomainEvent> void subscribe(EventSubscriber<T> subscriber) {
        subscribersMap
                .computeIfAbsent(subscriber.subscribedToEventType(), k -> new ArrayList<>())
                .add(subscriber);
    }

    // Publish an event to all relevant subscribers
    public <T extends DomainEvent> void publish(T event) {
        List<EventSubscriber<? extends DomainEvent>> subscribers = subscribersMap.get(event.getClass());
        if (subscribers != null) {
            for (EventSubscriber<? extends DomainEvent> subscriber : subscribers) {
                ((EventSubscriber<T>) subscriber).onEvent(event);  // Notify each subscriber
            }
        }
    }
}