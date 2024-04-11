package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.CommandRepository;
import at.fhv.lab1.commandclient.commands.BookRoomCommand;
import at.fhv.lab1.commandclient.commands.CancelBookingCommand;
import at.fhv.lab1.commandclient.commands.CreateCustomerCommand;
import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandRestController {
    private final CommandRepository repository;

    public CommandRestController(CommandRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/BookRoom", consumes = "application/json")
    public void bookRoom(@RequestBody BookRoomCommand command) {
        Booking booking = new Booking(command.getCustomerId(), command.getRoomId(), command.getStartDate(), command.getEndDate());
        repository.addBooking(booking);
    }

    @PostMapping(value = "/cancel-booking", consumes = "application/json")
    public void cancelBooking(@RequestBody CancelBookingCommand command) {
        repository.cancelBooking(command.getBookingId());
    }

    @PostMapping(value = "/create-customer", consumes = "application/json")
    public void createCustomer(@RequestBody CreateCustomerCommand command) {
        Customer customer = new Customer(command.getName(), command.getAddress(), command.getDateOfBirth());
        repository.createCustomer(customer);
    }
}
