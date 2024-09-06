package com.sagaflow.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionManager {

    public void beginTransaction(SagaContext context) {
        log.debug("Beginning transaction with ID: {}", context.getTransactionId());
    }

    public void endTransaction(SagaContext context) {
        log.debug("Ending transaction with ID: {}", context.getTransactionId());
    }
}