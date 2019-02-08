void testPayday() {
  FakeTransactionLog aLog = new FakeTransactionLog();
  Transaction t = new PaydayTransaction(getTestingDatabase(), aLog);

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
  void saveTransaction(Transaction);
}

public class TransactionLog implements TransactionRecorder {
  void saveTransaction(Transaction txn) {
    // ...
  }
}

public class FakeTransactionLog implements TransactionRecorder {
  void saveTransaction(Transaction txn) {
  }
}

// FakeTransactionLog doesn't exist yet
//   => need to extract an interface for TransactionLog class
//   & make it possible for PaydayTransaction to accept a FakeTransactionLog
