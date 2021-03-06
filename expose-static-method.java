class RSCWorkflow {
  public void validate(Packet packet) throws InvalidFlowException {
    validatePacket(packet);
  }

  public static void validatePacket(Packet packet) throws InvalidFlowException {
    if (packet.getOriginator().equals("MIA") {
        || packet.getLength() > MAX_LENGTH
        || !packet.hasValidCheckSum()) {
      throw new InvalidFlowException();
      }
    }
  }
}
