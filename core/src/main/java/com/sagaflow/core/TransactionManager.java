package com.sagaflow.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionManager {


    public void beginTransaction(SagaContext context) {
        TransactionContext transactionContext = new TransactionContext();
        transactionContext.setTransactionId(context.getTransactionId());
        transactionContext.setTimestamp(System.currentTimeMillis());
        context.setTransactionContext(transactionContext);  // Set the transaction context

        System.out.println("Beginning transaction with ID: " + transactionContext.getTransactionId());
    }

    public void endTransaction(SagaContext context) {
        System.out.println("Ending transaction with ID: " + context.getTransactionContext().getTransactionId());
    }

}