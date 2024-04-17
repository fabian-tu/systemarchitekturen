package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.EventHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventRestController {
  private final EventHandler handler;

  public EventRestController(EventHandler handler) {
    this.handler = handler;
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

  @PostMapping(value = "/delete-query-models", consumes = "application/json")
  public void deleteQueryModels() {
    handler.handleDeleteQueryModels();
  }
}
