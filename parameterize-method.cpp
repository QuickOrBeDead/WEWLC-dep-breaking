void TestCase::run(TestResult *result) {
  delete m_result;
  m_result = result;
  try {
    setUp();
    runTest(m_result);
  } catch (exception &e) {
    result->addFailure(e, this);
  }
  tearDown();
}

/* We can use a little forwarding method that keeps the original signature intact: */
void TestCase::run() {
  run(new TestResult);
}
