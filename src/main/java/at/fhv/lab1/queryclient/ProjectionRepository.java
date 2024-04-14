package at.fhv.lab1.queryclient;

import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProjectionRepository {
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<FreeRoom> freeRooms = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(UUID bookingId) {
        bookings.removeIf(booking -> booking.getBookingId() == bookingId);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<FreeRoom> getFreeRooms() {
        return freeRooms;
    }
}
