# SagaFlow Framework

## Overview

SagaFlow is a framework that orchestrates distributed transactions (sagas) in a microservices architecture. It allows for defining steps in a saga and handles success, failure, and compensation actions.

### Core Components:
- **SagaOrchestrator**: Manages the execution of saga steps.
- **SagaStep**: Represents individual steps in the saga.
- **SagaContext**: Holds metadata about the current saga execution.
- **StepExecutor**: Executes individual steps in the saga.
- **ErrorHandlingTransactionManager**: Manages transactional behavior across saga steps.

### Example Usage

Here’s how you define and execute a simple saga:

```java
SagaOrchestrator orchestrator = new SagaOrchestrator();
orchestrator.addStep(new ExampleStep1());
orchestrator.addStep(new ExampleStep2());

SagaContext context = new SagaContext();
orchestrator.executeSaga(context);
