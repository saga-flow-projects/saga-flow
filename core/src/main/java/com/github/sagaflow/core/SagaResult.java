package com.github.sagaflow.core;

import lombok.Value;

@Value
public class SagaResult {

    boolean success;
    String message;
}
