
#### `eventstream.md` (EventStream Documentation)

```markdown
# EventStream Framework

## Overview

EventStream is an extension to the SagaFlow framework, providing a domain event publisher/subscriber mechanism. It allows publishing domain events throughout a system and enables components to subscribe to specific or generic events.

### Key Components:
- **EventBus**: Routes events between publishers and subscribers.
- **EventPublisher**: Publishes events to the EventBus.
- **EventSubscriber**: Listens for domain events.
- **DomainEvent**: Base interface for all domain events.

### Advanced Features:
- **Event Hierarchy**: Subscribers can listen for events based on superclass or interface types, making the system flexible for different event types.
- **Dependency Inversion**: EventStream is designed as a decoupled extension of the core SagaFlow framework.

### Example Usage

```java
EventBus eventBus = new EventBus();
EventPublisher eventPublisher = new EventPublisher(eventBus);

// Subscribe to DomainEvent
eventBus.subscribe(new GenericEventSubscriber());

// Publish an event
OrderCreatedEvent event = new OrderCreatedEvent("12345");
eventPublisher.publish(event);
