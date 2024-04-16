package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.EventHandler;
import at.fhv.lab1.queryclient.ProjectionRepository;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping(value = "/room-created-event", consumes = "application/json")
    public void roomCreatedEvent(@RequestBody RoomCreatedEvent event) {
        handler.handleRoomCreatedEvent(event);
    }

    @GetMapping(value = "/get-bookings", produces = "application/json")
    public List<Booking> getBookings(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return repository.getBookingsByDate(startDate, endDate);
    }

    @GetMapping(value = "/get-free-rooms", produces = "application/json")
    public List<FreeRoom> getFreeRooms(
            @RequestParam LocalDate availableFrom,
            @RequestParam LocalDate availableTo,
            @RequestParam int numberOfBeds) {
        return repository.getFreeRooms(availableFrom, availableTo, numberOfBeds);
    }

    @GetMapping(value = "/get-customers", produces = "application/json")
    public List<Customer> getCustomers(@RequestParam(required = false) String name) {
        if (name != null) {
            return repository.getCustomersByName(name);
        }

        return repository.getCustomers();
    }
}
