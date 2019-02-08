// Let’s suppose that we don’t want to have a dependency on the MimeMessage class when we are testing. It uses a variable named session, and we will not have a real session when we are testing.
// If we want to separate out the dependency on MimeMessage, we can make createForwardMessage protected and override it in a new subclass that we make just for testing:

class MessageForwarder {
  private Message createForwardMessage(Session session, Message message)
    throws MessagingException, IOException {

    MimeMessage forward = new MimeMessage(session);

    forward.setFrom(getFromAddress(message));
    forward.setReplyTo(new Address [] { new InternetAddress(listAddress)});
    forward.addRecipients(Message.RecipientType.TO, listAddress);
    forward.addRecipients(Message.RecipientType.BCC, getMailListAddresses());
    forward.setSubject(transformedSubject(message.getSubject()));
    forward.setSentDate(message.getSentDate());
    forward.addHeader(LOOP_HEADER, listAddress);
    buildForwardContent(message, forward);
    return forward;
  }
}
