package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.DomainRepository;
import at.fhv.lab1.commandclient.commands.BookRoomCommand;
import at.fhv.lab1.commandclient.commands.CancelBookingCommand;
import at.fhv.lab1.commandclient.commands.CreateCustomerCommand;
import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandRestController {
    private final DomainRepository repository;

    public CommandRestController(DomainRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/book-room", consumes = "application/json")
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
