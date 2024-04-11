package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.model.Booking;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.model.Room;
import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommandRepository {
    private final EventPublisher eventPublisher;
    private final ArrayList<Booking> bookings = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Room> rooms = new ArrayList<>();
    private int bokingId = 0;
    private int customerId = 0;

    CommandRepository(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void addBooking(Booking booking) {
        booking.setBookingId(bokingId++);
        bookings.add(booking);

        RoomBookedEvent event = new RoomBookedEvent(booking.getBookingId(), booking.getRoomId(), booking.getCustomerId(), booking.getStartDate(), booking.getEndDate());
        eventPublisher.publishRoomBookedEvent(event);
    }

    public void cancelBooking(int bookingId) {
        Booking booking = bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);
        bookings.remove(booking);

        BookingCancelledEvent event = new BookingCancelledEvent(booking.getBookingId(), booking.getStartDate(), booking.getEndDate());
        eventPublisher.publishBookingCancelledEvent(event);
    }

    public void createCustomer(Customer customer) {
        customer.setId(customerId++);
        customers.add(customer);

        CustomerCreatedEvent event = new CustomerCreatedEvent(customer.getId(), customer.getName(), customer.getAddress(), customer.getDateOfBirth());
        eventPublisher.publishCustomerCreatedEvent(event);
    }
}
