
#### `advanced_features.md` (Advanced Features)

```markdown
# Advanced Features

## 1. Dependency Inversion

The **SagaFlow** core does not depend on any extensions, including **EventStream**. It follows the **Dependency Inversion Principle (DIP)**, allowing extensions to integrate seamlessly through interfaces like `SagaEventListener`.

### Example: EventStream Integration with SagaFlow

```java
SagaOrchestrator orchestrator = new SagaOrchestrator();
EventStreamSagaListener sagaListener = new EventStreamSagaListener(new EventPublisher(eventBus));

orchestrator.addEventListener(sagaListener);
orchestrator.addStep(new ExampleStep1());
orchestrator.addStep(new ExampleStep2());

orchestrator.executeSaga(new SagaContext());
