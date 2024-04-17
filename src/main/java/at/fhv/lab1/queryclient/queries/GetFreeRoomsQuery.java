package at.fhv.lab1.queryclient.queries;

import java.time.LocalDate;

public class GetFreeRoomsQuery {
  private LocalDate startDate;
  private LocalDate endDate;
  private int numberOfBeds;

  public GetFreeRoomsQuery(LocalDate startDate, LocalDate endDate, int numberOfBeds) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.numberOfBeds = numberOfBeds;
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

  public int getNumberOfBeds() {
    return numberOfBeds;
  }

  public void setNumberOfBeds(int numberOfBeds) {
    this.numberOfBeds = numberOfBeds;
  }

  @Override
  public String toString() {
    return "GetFreeRoomsQuery{"
        + "startDate="
        + startDate
        + ", endDate="
        + endDate
        + ", numberOfBeds="
        + numberOfBeds
        + '}';
  }
}
