package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.QueryRepository;
import at.fhv.lab1.queryclient.queries.Booking;
import at.fhv.lab1.queryclient.queries.Customer;
import at.fhv.lab1.queryclient.queries.FreeRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class QueryRestController {
    private final QueryRepository repository;

    public QueryRestController(QueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/get-bookings", produces = "application/json")
    public ArrayList<Booking> getBookings() {

        return new ArrayList<>();
    }

    @GetMapping(value = "/get-free-rooms", produces = "application/json")
    public ArrayList<FreeRoom> getFreeRooms() {

        return new ArrayList<>();
    }

    @GetMapping(value = "/get-customers", produces = "application/json")
    public ArrayList<Customer> getCustomers() {

        return new ArrayList<>();
    }

    @PostMapping(value = "/room-booked-event", consumes = "application/json")
    public void addRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        //repository.processRoomBookedEvent(event);
    }

    @PostMapping(value = "/booking-cancelled-event", consumes = "application/json")
    public void addBookingCancelledEvent(@RequestBody BookingCancelledEvent event) {
        //repository.processBookingCancelledEvent(event);
    }

    @PostMapping(value = "/customer-created-event", consumes = "application/json")
    public void addCustomerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
        //repository.processCustomerCreatedEvent(event);
    }
}
