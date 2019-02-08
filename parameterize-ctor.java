public class MailChecker {
  public MailChecker (int checkPeriodSeconds) {
    this(new MailReceiver(), checkPeriodSeconds);
  }
}

public class MailChecker {
  public MailChecker (MailerReceiver receiver, int checkPeriodSeconds) {
    this.receiver = receiver;
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
}
