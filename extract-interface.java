void testPayday() {
  FakeTransactionLog aLog = new FakeTransactionLog();
  Transaction t = new PaydayTransaction(getTestingDatabase());

  t.run();

  assertEquals(getSampleCheck(12),
      getTestingDatabase().findCheck(12));
}

public class PaydayTransaction extends Transaction {
  public PaydayTransaction(PayrollDatabase db, TransactionRecorder log) {
    super(db, log);
  }

  public void run() {
    for(Iterator it = db.getEmployees(); it.hasNext()) {
      Employee e = (Employee)it.next();
      if (e.isPayday(date)) {
        e.pay();
      }
    }
    log.saveTransaction(this);
  }
}

interface TransactionRecorder {
}

public class TransactionLog implements TransactionRecorder {
  // ...
}

public class FakeTransactionLog implements TransactionRecorder {
  // ...
}

// FakeTransactionLog doesn't exist yet
//   => need to extract an interface for TransactionLog class
//   & make it possible for PaydayTransaction to accept a FakeTransactionLog
