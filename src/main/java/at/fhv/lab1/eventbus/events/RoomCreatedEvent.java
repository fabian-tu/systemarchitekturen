package at.fhv.lab1.eventbus.events;

import java.util.UUID;

public class RoomCreatedEvent extends Event {
  private UUID roomId;
  private int roomNumber;
  private int beds;
  private float pricePerNight;

  public RoomCreatedEvent() {}

  public RoomCreatedEvent(UUID roomId, int roomNumber, int beds, float pricePerNight) {
    this.roomId = roomId;
    this.roomNumber = roomNumber;
    this.beds = beds;
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

  public int getBeds() {
    return beds;
  }

  public void setBeds(int beds) {
    this.beds = beds;
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
        + ", beds="
        + beds
        + ", pricePerNight="
        + pricePerNight
        + '}';
  }
}
