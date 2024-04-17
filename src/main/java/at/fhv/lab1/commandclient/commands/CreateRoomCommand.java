package at.fhv.lab1.commandclient.commands;

public class CreateRoomCommand {
  private int roomNumber;
  private int numberOfBeds;
  private float pricePerNight;

  public CreateRoomCommand() {}

  public CreateRoomCommand(int roomNumber, int numberOfBeds, float pricePerNight) {
    this.roomNumber = roomNumber;
    this.numberOfBeds = numberOfBeds;
    this.pricePerNight = pricePerNight;
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
    return "CreatRoomCommand{"
        + "roomNumber="
        + roomNumber
        + ", numberOfBeds="
        + numberOfBeds
        + ", pricePerNight="
        + pricePerNight
        + '}';
  }
}
