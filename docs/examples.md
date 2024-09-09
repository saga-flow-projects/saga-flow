# Code Examples

## 1. SagaFlow Basic Example

This example demonstrates how to create a basic saga with two steps and execute it using the **SagaFlow** framework.

### Code:

```java
import com.sagaflow.core.*;

public class SagaFlowBasicExample {

    public static void main(String[] args) {
        // Create a SagaOrchestrator
        SagaOrchestrator orchestrator = new SagaOrchestrator();

        // Define saga steps
        SagaStep step1 = new ExampleStep1();
        SagaStep step2 = new ExampleStep2();

        // Add steps to orchestrator
        orchestrator.addStep(step1);
        orchestrator.addStep(step2);

        // Create saga context
        SagaContext context = new SagaContext();

        // Execute the saga
        SagaResult result = orchestrator.executeSaga(context);
        System.out.println("Saga result: " + result.getMessage());
    }
}

// Define example steps
class ExampleStep1 implements SagaStep {
    @Override
    public void execute(SagaContext context) throws SagaException {
        System.out.println("Executing Step 1");
        // Simulate step logic
    }

    @Override
    public void rollback(SagaContext context) {
        System.out.println("Rolling back Step 1");
        // Simulate rollback logic
    }
}

class ExampleStep2 implements SagaStep {
    @Override
    public void execute(SagaContext context) throws SagaException {
        System.out.println("Executing Step 2");
        // Simulate step logic
    }

    @Override
    public void rollback(SagaContext context) {
        System.out.println("Rolling back Step 2");
        // Simulate rollback logic
    }
}
