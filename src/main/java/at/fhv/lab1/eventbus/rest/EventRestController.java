package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventPublisher;
import at.fhv.lab1.eventbus.EventRepository;
import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {
    private final EventRepository repository;

    public EventRestController(EventRepository repository, EventPublisher publisher) {
        this.repository = repository;
    }

    @PostMapping(value = "/room-booked-event", consumes = "application/json")
    public void addRoomBookedEvent(@RequestBody RoomBookedEvent event) {
        repository.processRoomBookedEvent(event);
    }

    @PostMapping(value = "/booking-cancelled-event", consumes = "application/json")
    public void addBookingCancelledEvent(@RequestBody BookingCancelledEvent event) {
        repository.processBookingCancelledEvent(event);
    }

    @PostMapping(value = "/customer-created-event", consumes = "application/json")
    public void addCustomerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
        repository.processCustomerCreatedEvent(event);
    }
}
