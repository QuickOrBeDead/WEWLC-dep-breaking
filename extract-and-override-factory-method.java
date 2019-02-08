// Hard-coded initialization work in constructors can be very hard to work around in testing.

public class WorkflowEngine {
  public WorkflowEngine() {
    Reader reader = new ModelReader(AppConfig.getDryConfiguration());
    Persister persister = new XMLStore(AppConfiguration.getDryConfiguration());
    this.tm = new TransactionManager(reader, persister);
  }
}

// WorkflowEngine creates a TransactionManager in its constructor. If the creation was someplace else, we could introduce some separation more easily. One of the options we have is to use Extract and Override Factory Method.
