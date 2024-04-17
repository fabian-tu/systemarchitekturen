package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.CommandHandler;
import at.fhv.lab1.commandclient.commands.BookRoomCommand;
import at.fhv.lab1.commandclient.commands.CancelBookingCommand;
import at.fhv.lab1.commandclient.commands.CreateCustomerCommand;
import at.fhv.lab1.commandclient.commands.CreateRoomCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandRestController {
  private final CommandHandler handler;

  public CommandRestController(CommandHandler handler) {
    this.handler = handler;
  }

  @PostMapping(value = "/book-room", consumes = "application/json")
  public void bookRoom(@RequestBody BookRoomCommand command) {
    handler.handleBookRoomCommand(command);
  }

  @PostMapping(value = "/cancel-booking", consumes = "application/json")
  public void cancelBooking(@RequestBody CancelBookingCommand command) {
    handler.handleCancelBookingCommand(command);
  }

  @PostMapping(value = "/create-customer", consumes = "application/json")
  public void createCustomer(@RequestBody CreateCustomerCommand command) {
    handler.handleCreateCustomerCommand(command);
  }

  @PostMapping(value = "/create-room", consumes = "application/json")
  public void createRoom(@RequestBody CreateRoomCommand command) {
    handler.handleCreateRoomCommand(command);
  }
}
