// Hard-coded initialization work in constructors can be very hard to work around in testing.

public class WorkflowEngine {
  public WorkflowEngine() {
    this.tm = makeTransactionManager();
  }

  protected TransactionManager makeTransactionManager() {
    Reader reader = new ModelReader(AppConfiguration.getDryConfiguration());
    Persister persister = new XMLStore(AppConfiguration.getDryConfiguration());
    return new TransactionManager(reader, persister);
  }
}

// When we have that factory method, we can subclass and override it so that we can return a new transaction manager whenever we need one:
public class TestWorkflowEngine extends WorkflowEngine {
  protected TransactionManager makeTransactionManager() {
    return new FakeTransactionManager();
  }
}

// WorkflowEngine creates a TransactionManager in its constructor. If the creation was someplace else, we could introduce some separation more easily. One of the options we have is to use Extract and Override Factory Method.
