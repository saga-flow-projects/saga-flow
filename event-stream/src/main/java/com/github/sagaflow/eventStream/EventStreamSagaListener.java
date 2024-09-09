package com.github.sagaflow.eventStream;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaEventListener;
import com.github.sagaflow.core.steps.SagaStep;
import com.github.sagaflow.eventStream.event.SagaCompletedEvent;
import com.github.sagaflow.eventStream.event.SagaFailedEvent;
import com.github.sagaflow.eventStream.event.SagaStepCompletedEvent;
import com.github.sagaflow.eventStream.event.SagaStepFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventStreamSagaListener implements SagaEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventStreamSagaListener.class);
    private final EventPublisher eventPublisher;

    public EventStreamSagaListener(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onStepCompleted(SagaContext context, SagaStep step) {
        logger.info("Step completed: {}", step.getClass().getSimpleName());
        eventPublisher.publish(new SagaStepCompletedEvent(step.getClass().getSimpleName()));
    }

    @Override
    public void onStepFailed(SagaContext context, SagaStep step, Exception exception) {
        logger.warn("Step failed: {}", step.getClass().getSimpleName());
        eventPublisher.publish(new SagaStepFailedEvent(step.getClass().getSimpleName(), exception));
    }

    @Override
    public void onSagaCompleted(SagaContext context) {
        logger.info("Saga completed successfully.");
        eventPublisher.publish(new SagaCompletedEvent(context.getTransactionId()));
    }

    @Override
    public void onSagaFailed(SagaContext context, Exception exception) {
        logger.error("Saga failed.", exception);
        eventPublisher.publish(new SagaFailedEvent(context.getTransactionId(), exception));
    }
}
