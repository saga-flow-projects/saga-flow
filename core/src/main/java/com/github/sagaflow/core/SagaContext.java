package com.github.sagaflow.core;
import lombok.Data;

@Data
public class SagaContext {
    private String transactionId;
    private String userId;
    private TransactionContext transactionContext;
    private boolean timeoutExceeded = false;  // Flag for timeout
}