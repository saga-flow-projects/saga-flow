package com.sagaflow.core;


public interface SagaStep {
    void execute();
    void rollback();
}
