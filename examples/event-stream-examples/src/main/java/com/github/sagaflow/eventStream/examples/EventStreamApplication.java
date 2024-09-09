package com.github.sagaflow.eventStream.examples;


import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.eventStream.*;
import com.github.sagaflow.core.SagaOrchestrator;
import com.github.sagaflow.core.steps.SagaStep;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EventStreamApplication {

    public static void main(String[] args) {
        // Create EventBus
        EventBus eventBus = new EventBus();

        // Create EventPublisher
        EventPublisher eventPublisher = new EventPublisher(eventBus);

        // Create EventStreamSagaListener
        EventStreamSagaListener sagaListener = new EventStreamSagaListener(eventPublisher);

        // Create SagaOrchestrator and register listener
        SagaOrchestrator orchestrator = new SagaOrchestrator();
        orchestrator.addEventListener(sagaListener);

        // Define Saga steps (example)
        SagaStep step1 = new ExampleStep1();
        SagaStep step2 = new ExampleStep2();

        // Add steps to orchestrator
        orchestrator.addStep(step1);
        orchestrator.addStep(step2);

        // Execute the saga
        SagaContext context = new SagaContext();
        // Subscribing and publishing events
        eventBus.subscribe(new EventSubscriber<DomainEvent>() {
            @Override
            public void onEvent(DomainEvent event) {
                log.info("Received event: " + event);
            }

            @Override
            public Class<DomainEvent> subscribedToEventType() {
                return DomainEvent.class;
            }
        });


        orchestrator.executeSaga(context);


    }
}
