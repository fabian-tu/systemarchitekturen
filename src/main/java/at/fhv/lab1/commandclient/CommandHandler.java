package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.commands.*;
import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
  private final DomainRepository repository;
  private final EventPublisher eventPublisher;

  public CommandHandler(DomainRepository repository, EventPublisher eventPublisher) {
    this.repository = repository;
    this.eventPublisher = eventPublisher;
  }

  public void handleBookRoomCommand(BookRoomCommand command) {
    List<Room> rooms = repository.getRooms();
    List<Customer> customers = repository.getCustomers();
    List<Booking> bookings = repository.getBookings();

    if (rooms.stream().noneMatch(r -> r.getRoomId().equals(command.getRoomId()))) {
      System.out.println("Room with id " + command.getRoomId() + " not found");
      return;
    }

    if (customers.stream().noneMatch(c -> c.getCustomerId().equals(command.getCustomerId()))) {
      System.out.println("Customer with id " + command.getCustomerId() + " not found");
      return;
    }

    if (bookings.stream()
        .anyMatch(
            b ->
                b.getRoomId().equals(command.getRoomId())
                        && (!b.getStartDate().isBefore(command.getStartDate())
                            && !b.getStartDate().isAfter(command.getEndDate()))
                    || (!b.getEndDate().isBefore(command.getStartDate())
                        && !b.getEndDate().isAfter(command.getEndDate())))) {
      System.out.println("Room is already booked in this time frame");
      return;
    }

    Booking booking =
        new Booking(
            command.getCustomerId(),
            command.getRoomId(),
            command.getStartDate(),
            command.getEndDate());
    repository.addBooking(booking);

    RoomBookedEvent event =
        new RoomBookedEvent(
            booking.getBookingId(),
            booking.getCustomerId(),
            booking.getRoomId(),
            booking.getStartDate(),
            booking.getEndDate());
    eventPublisher.publishRoomBookedEvent(event);
  }

  public void handleCancelBookingCommand(CancelBookingCommand command) {
    List<Booking> bookings = repository.getBookings();

    Booking booking =
        bookings.stream()
            .filter(b -> b.getBookingId().equals(command.getBookingId()))
            .findFirst()
            .orElse(null);

    if (booking == null) {
      System.out.println("Booking with id " + command.getBookingId() + " not found");
      return;
    }

    repository.removeBooking(booking);

    BookingCancelledEvent event = new BookingCancelledEvent(booking.getBookingId());
    eventPublisher.publishBookingCancelledEvent(event);
  }

  public void handleCreateCustomerCommand(CreateCustomerCommand command) {
    Customer customer =
        new Customer(command.getName(), command.getAddress(), command.getDateOfBirth());
    repository.addCustomer(customer);

    CustomerCreatedEvent event =
        new CustomerCreatedEvent(
            customer.getCustomerId(),
            customer.getName(),
            customer.getAddress(),
            customer.getDateOfBirth());
    eventPublisher.publishCustomerCreatedEvent(event);
  }

  public void handleCreateRoomCommand(CreateRoomCommand command) {
    List<Room> rooms = repository.getRooms();

    if (rooms.stream().anyMatch(r -> r.getRoomNumber() == command.getRoomNumber())) {
      System.out.println("Room with number " + command.getRoomNumber() + " already exists");
      return;
    }

    Room room =
        new Room(command.getRoomNumber(), command.getNumberOfBeds(), command.getPricePerNight());
    repository.addRoom(room);

    RoomCreatedEvent event =
        new RoomCreatedEvent(
            room.getRoomId(), room.getRoomNumber(), room.getBeds(), room.getPricePerNight());
    eventPublisher.publishRoomCreatedEvent(event);
  }
}
