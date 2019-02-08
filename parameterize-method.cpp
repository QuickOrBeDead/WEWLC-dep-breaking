void TestCase::run() {
  delete m_result;
  m_result = new TestResult;
  try {
    setUp();
    runTest(m_result);
  } catch (exception &e) {
    result->addFailure(e, this);
  }
  tearDown();
}
