// want to make the receiver injectable
public class MailChecker {
  public MailChecker (int checkPeriodSeconds) {
    this.receiver = new MailReceiver();
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
}

public class MailChecker {
  public MailChecker (MailerReceiver receiver, int checkPeriodSeconds) {
    this.receiver = receiver;
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
}
