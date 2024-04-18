package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventHandler;
import at.fhv.lab1.eventbus.events.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
  public void handleRoomBookedEvent(@RequestBody RoomBookedEvent event) {
    handler.handleRoomBookedEvent(event);
  }

  @PostMapping(value = "/booking-cancelled-event", consumes = "application/json")
  public void handleBookingCancelledEvent(@RequestBody BookingCancelledEvent event) {
    handler.handleBookingCancelledEvent(event);
  }

  @PostMapping(value = "/customer-created-event", consumes = "application/json")
  public void handleCustomerCreatedEvent(@RequestBody CustomerCreatedEvent event) {
    handler.handleCustomerCreatedEvent(event);
  }

  @PostMapping(value = "/room-created-event", consumes = "application/json")
  public void handleRoomCreatedEvent(@RequestBody RoomCreatedEvent event) {
    handler.handleRoomCreatedEvent(event);
  }

  @PostMapping(value = "/restore-query-models")
  public void handleRestoreQueryModels() {
    handler.handleRestoreEvents();
  }

  @GetMapping(value = "/get-events")
  public List<Event> handleGetEvents() {
    return handler.handleGetEvents();
  }
}
