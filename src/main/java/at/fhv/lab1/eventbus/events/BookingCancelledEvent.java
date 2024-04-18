package at.fhv.lab1.eventbus.events;

import java.util.UUID;

public class BookingCancelledEvent extends Event {
  private UUID bookingId;

  public BookingCancelledEvent() {}

  public BookingCancelledEvent(UUID bookingId) {
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
    return "BookingCancelledEvent{"
        + "type="
        + getType()
        + ", bookingId="
        + bookingId
        + ", timestamp="
        + getTimestamp()
        + '}';
  }
}
