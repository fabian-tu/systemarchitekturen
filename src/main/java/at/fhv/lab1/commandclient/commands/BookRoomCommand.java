package at.fhv.lab1.commandclient.commands;

import java.time.LocalDate;
import java.util.UUID;

public class BookRoomCommand {
  private UUID customerId;
  private UUID roomId;
  private LocalDate startDate;
  private LocalDate endDate;

  public BookRoomCommand() {}

  public BookRoomCommand(UUID customerId, UUID roomId, LocalDate startDate, LocalDate endDate) {
    this.customerId = customerId;
    this.roomId = roomId;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public UUID getRoomId() {
    return roomId;
  }

  public void setRoomId(UUID roomId) {
    this.roomId = roomId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "Booking{"
        + "customerId="
        + customerId
        + ", roomId="
        + roomId
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + '}';
  }
}
