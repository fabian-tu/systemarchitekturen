package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

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

        RoomBookedEvent event = new RoomBookedEvent(booking.getBookingId(), booking.getRoomId(), booking.getCustomerId(), booking.getStartDate(), booking.getEndDate());
        eventPublisher.publishRoomBookedEvent(event);
    }

    public void cancelBooking(UUID bookingId) {
        Booking booking = bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);
        bookings.remove(booking);

        if (booking != null) {
            BookingCancelledEvent event = new BookingCancelledEvent(booking.getBookingId());
            eventPublisher.publishBookingCancelledEvent(event);
        }
    }

    public void createCustomer(Customer customer) {
        customer.setCustomerId(UUID.randomUUID());
        customers.add(customer);

        CustomerCreatedEvent event = new CustomerCreatedEvent(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getDateOfBirth());
        eventPublisher.publishCustomerCreatedEvent(event);
    }
}
