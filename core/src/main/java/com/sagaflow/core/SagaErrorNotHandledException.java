package com.sagaflow.core;

public class SagaErrorNotHandledException extends SagaException {
    public SagaErrorNotHandledException(String message) {
        super(message);
    }

    public SagaErrorNotHandledException(String message, Throwable cause) {
        super(message, cause);
    }
}
