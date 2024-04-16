package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class DomainRepository {
  private final EventPublisher eventPublisher;
  private final ArrayList<Booking> bookings = new ArrayList<>();
  private final ArrayList<Customer> customers = new ArrayList<>();
  private final ArrayList<Room> rooms = new ArrayList<>();

  DomainRepository(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void addBooking(Booking booking) {
    booking.setBookingId(UUID.randomUUID());
    bookings.add(booking);
    System.out.println("Booking added: " + booking);

    RoomBookedEvent event =
        new RoomBookedEvent(
            booking.getBookingId(),
            booking.getCustomerId(),
            booking.getRoomId(),
            booking.getStartDate(),
            booking.getEndDate());
    eventPublisher.publishRoomBookedEvent(event);
  }

  public void cancelBooking(UUID bookingId) {
    System.out.println("Cancelling booking: " + bookingId);
    Booking booking =
        bookings.stream().filter(b -> b.getBookingId().equals(bookingId)).findFirst().orElse(null);

    if (booking != null) {
      bookings.remove(booking);
      System.out.println("Booking cancelled: " + booking);

      BookingCancelledEvent event = new BookingCancelledEvent(booking.getBookingId());
      eventPublisher.publishBookingCancelledEvent(event);
    }
  }

  public void createCustomer(Customer customer) {
    customer.setCustomerId(UUID.randomUUID());
    customers.add(customer);
    System.out.println("Customer created: " + customer);

    CustomerCreatedEvent event =
        new CustomerCreatedEvent(
            customer.getCustomerId(),
            customer.getName(),
            customer.getAddress(),
            customer.getDateOfBirth());
    eventPublisher.publishCustomerCreatedEvent(event);
  }

  public void addRoom(Room room) {
    room.setRoomId(UUID.randomUUID());
    rooms.add(room);
    System.out.println("Room created: " + room);

    RoomCreatedEvent event =
        new RoomCreatedEvent(
            room.getRoomId(), room.getRoomNumber(), room.getBeds(), room.getPricePerNight());
    eventPublisher.publishRoomCreatedEvent(event);
  }
}
