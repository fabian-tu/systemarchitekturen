package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventHandler {
  private final EventRepository repository;
  private final EventPublisher publisher;

  public EventHandler(EventRepository repository, EventPublisher publisher) {
    this.repository = repository;
    this.publisher = publisher;
  }

  public void handleRoomBookedEvent(RoomBookedEvent event) {
    repository.addRoomBookedEvent(event);
    publisher.publishRoomBookedEvent(event);
  }

  public void handleBookingCancelledEvent(BookingCancelledEvent event) {
    repository.addBookingCancelledEvent(event);
    publisher.publishBookingCancelledEvent(event);
  }

  public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
    repository.addCustomerCreatedEvent(event);
    publisher.publishCustomerCreatedEvent(event);
  }

  public void handleRoomCreatedEvent(RoomCreatedEvent event) {
    repository.addRoomCreatedEvent(event);
    publisher.publishRoomCreatedEvent(event);
  }

  public void handleRestoreQueryModels() {
    // repository.getEvents();
  }
  public void handleRestoreEvents() {
    List<Event> events = EventRepository.getAllEvents();
    for (Event event : events) {
      publisher.publishEventToQueryClient(event);
    }
  }
}
