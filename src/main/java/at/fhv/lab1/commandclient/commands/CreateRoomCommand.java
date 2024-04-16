package at.fhv.lab1.commandclient.commands;

public class CreateRoomCommand {
  private int roomNumber;
  private int beds;
  private float pricePerNight;

  public CreateRoomCommand() {}

  public CreateRoomCommand(int roomNumber, int beds, float pricePerNight) {
    this.roomNumber = roomNumber;
    this.beds = beds;
    this.pricePerNight = pricePerNight;
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
    return "CreatRoomCommand{"
        + "roomNumber="
        + roomNumber
        + ", beds="
        + beds
        + ", pricePerNight="
        + pricePerNight
        + '}';
  }
}
