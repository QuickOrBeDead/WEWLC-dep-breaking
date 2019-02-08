void testPayday() {
  FakeTransactionLog aLog = new FakeTransactionLog();
  Transaction t = new PaydayTransaction(getTestingDatabase());

  t.run();

  assertEquals(getSampleCheck(12),
      getTestingDatabase().findCheck(12));
}

// FakeTransactionLog doesn't exist yet
//   => need to extract an interface for TransactionLog class
//   & make it possible for PaydayTransaction to accept a FakeTransactionLog
