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

    // Publish an event to all relevant subscribers, including subscribers to superclasses/interfaces
    public <T extends DomainEvent> void publish(T event) {
        // Notify subscribers for the specific event type
        notifySubscribersForEventType(event.getClass(), event);
        Class<?>[] interfaces = event.getClass().getInterfaces();
        callInterfaces(event, interfaces);

        // Notify subscribers for parent types (superclasses/interfaces)
        Class<?> superclass = event.getClass().getSuperclass();
        while (superclass != null && DomainEvent.class.isAssignableFrom(superclass)) {
            notifySubscribersForEventType(superclass, event);
            callInterfaces(event, superclass.getInterfaces());
            superclass = superclass.getSuperclass();
        }

        // Notify subscribers for implemented interfaces

    }

    private <T extends DomainEvent> void callInterfaces(T event, Class<?>[] interfaces) {
        for (Class<?> eventInterface : interfaces) {
            if (DomainEvent.class.isAssignableFrom(eventInterface)) {
                notifySubscribersForEventType(eventInterface, event);
            }
        }
    }

    // Helper method to notify subscribers for a given event type
    @SuppressWarnings("unchecked")
    private <T extends DomainEvent> void notifySubscribersForEventType(Class<?> eventType, T event) {
        List<EventSubscriber<? extends DomainEvent>> subscribers = subscribersMap.get(eventType);
        if (subscribers != null) {
            for (EventSubscriber<? extends DomainEvent> subscriber : subscribers) {
                ((EventSubscriber<T>) subscriber).onEvent(event);  // Notify each subscriber
            }
        }
    }
}