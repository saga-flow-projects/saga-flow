package com.sagaflow.core;

import lombok.Data;

@Data
public class CompensationContext {
    private String transactionId;
    private String reason;
    // Any additional metadata needed for compensation
}
