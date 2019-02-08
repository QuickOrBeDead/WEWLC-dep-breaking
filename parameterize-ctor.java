// want to make the receiver injectable
public class MailChecker {
  public MailChecker (int checkPeriodSeconds) {
    this.receiver = new MailReceiver();
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
}
