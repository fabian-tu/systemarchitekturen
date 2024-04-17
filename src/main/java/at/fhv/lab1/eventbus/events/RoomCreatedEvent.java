package at.fhv.lab1.eventbus.events;

import java.util.UUID;

public class RoomCreatedEvent extends Event {
  private UUID roomId;
  private int roomNumber;
  private int numberOfBeds;
  private float pricePerNight;

  public RoomCreatedEvent() {}

  public RoomCreatedEvent(UUID roomId, int roomNumber, int numberOfBeds, float pricePerNight) {
    this.roomId = roomId;
    this.roomNumber = roomNumber;
    this.numberOfBeds = numberOfBeds;
    this.pricePerNight = pricePerNight;
  }

  public UUID getRoomId() {
    return roomId;
  }

  public void setRoomId(UUID roomId) {
    this.roomId = roomId;
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public int getNumberOfBeds() {
    return numberOfBeds;
  }

  public void setNumberOfBeds(int numberOfBeds) {
    this.numberOfBeds = numberOfBeds;
  }

  public float getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(float pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  @Override
  public String toString() {
    return "RoomCreatedEvent{"
        + "roomId="
        + roomId
        + ", roomNumber="
        + roomNumber
        + ", numberOfBeds="
        + numberOfBeds
        + ", pricePerNight="
        + pricePerNight
        + '}';
  }
}
