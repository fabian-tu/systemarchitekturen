package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.EventHandler;
import at.fhv.lab1.queryclient.ProjectionRepository;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryRestController {
    private final EventHandler handler;
    private final ProjectionRepository repository;

    public QueryRestController(EventHandler handler, ProjectionRepository repository) {
        this.handler = handler;
        this.repository = repository;
    }

    @PostMapping(value = "/room-booked-event", consumes = "application/json")
    public void roomBookedEvent(@RequestBody RoomBookedEvent event) {
        handler.handleRoomBookedEvent(event);
    }

    @PostMapping(value = "/booking-cancelled-event", consumes = "application/json")
    public void bookingCancelledEvent(@RequestBody BookingCancelledEvent event) {
        handler.handleBookingCancelledEvent(event);
    }

    @PostMapping(value = "/customer-created-event", consumes = "application/json")
    public void customerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
        handler.handleCustomerCreatedEvent(event);
    }

    @GetMapping(value = "/get-bookings", produces = "application/json")
    public List<Booking> getBookings() {
        return repository.getBookings();
    }

    @GetMapping(value = "/get-free-rooms", produces = "application/json")
    public List<FreeRoom> getFreeRooms() {
        return repository.getFreeRooms();
    }

    @GetMapping(value = "/get-customers", produces = "application/json")
    public List<Customer> getCustomers() {
        return repository.getCustomers();
    }
}
