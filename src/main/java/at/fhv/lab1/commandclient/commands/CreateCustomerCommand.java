package at.fhv.lab1.commandclient.commands;

import java.time.LocalDate;

public class CreateCustomerCommand {
  private String name;
  private String address;
  private LocalDate dateOfBirth;

  public CreateCustomerCommand() {}

  public CreateCustomerCommand(String name, String address, LocalDate dateOfBirth) {
    this.name = name;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public String toString() {
    return "CreateCustomerCommand{"
        + "name='"
        + name
        + ", address='"
        + address
        + ", dateOfBirth="
        + dateOfBirth
        + '}';
  }
}
