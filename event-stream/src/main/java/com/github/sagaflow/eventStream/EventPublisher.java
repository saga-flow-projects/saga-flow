package com.github.sagaflow.eventStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);
    private final EventBus eventBus;

    public EventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(DomainEvent event) {
        logger.info("Publishing event: {}", event.eventType());
        eventBus.publish(event);
    }
}