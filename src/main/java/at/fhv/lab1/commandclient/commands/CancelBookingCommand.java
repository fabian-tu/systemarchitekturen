package at.fhv.lab1.commandclient.commands;

import java.util.UUID;

public class CancelBookingCommand {
  private UUID bookingId;

  public CancelBookingCommand() {}

  public CancelBookingCommand(UUID bookingId) {
    this.bookingId = bookingId;
  }

  public UUID getBookingId() {
    return bookingId;
  }

  public void setBookingId(UUID bookingId) {
    this.bookingId = bookingId;
  }

  @Override
  public String toString() {
    return "CancelBookingCommand{" + "bookingId=" + bookingId + '}';
  }
}
