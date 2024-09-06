package com.github.sagaflow.core;

import lombok.Data;

@Data
public class TransactionContext {
    private String transactionId;
    private long timestamp;
    private String status;
}