package com.sagaflow.core;
import lombok.Data;

@Data
public class SagaContext {
    private String transactionId;
    private String userId;
}