

# SagaFlow

## Overview

SagaFlow is a Java-based library designed to manage and execute distributed transactions using the Saga pattern. It provides a robust mechanism to handle retries and ensure consistency across multiple services.

## Features

- **Saga Pattern Implementation**: Manage distributed transactions with ease.
- **Retry Mechanism**: Configurable retry logic for handling transient failures.
- **Logging**: Integrated logging using SLF4J.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.github.sagaflow</groupId>
    <artifactId>sagaflow-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

To correct the usage section and include the `SagaOrchestrator` as the main class, here is an updated example:

### Usage

#### SagaOrchestrator

To correct the usage section and include the `SagaOrchestrator` as the main class, here is an updated example:

### Usage

To correct the usage section and include the `SagaOrchestrator` as the main class, here is an updated example:

### Usage

#### SagaOrchestrator

The `SagaOrchestrator` class is responsible for managing the execution of multiple `SagaStep` instances. It coordinates the steps and handles retries using the `SagaRetryManager`.

```java
package com.github.sagaflow.core.example;

import com.github.sagaflow.core.SagaContext;
import com.github.sagaflow.core.SagaOrchestrator;
import com.github.sagaflow.core.SagaResult;
import com.github.sagaflow.core.example.steps.PaymentCompensationStep;
import com.github.sagaflow.core.example.steps.PaymentSagaStep;

public class SagaFlowCoreApplication {

    public static void main(String[] args) {
        SagaOrchestrator orchestrator = new SagaOrchestrator();
        SagaContext context = new SagaContext();
        context.setTransactionId("TX123456");
        context.setUserId("User001");

        // Add execution steps
        orchestrator.addStep(new PaymentSagaStep());

        // Add compensation steps (for rollback)
        orchestrator.addCompensationStep(new PaymentCompensationStep());

        // Execute the saga
        SagaResult result = orchestrator.executeSaga(context);
        System.out.println("Saga Result: " + result.getMessage());
    }
}
```


## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any questions or suggestions, please open an issue on GitHub.

