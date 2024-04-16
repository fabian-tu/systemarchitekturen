package at.fhv.lab1.queryclient;

import at.fhv.lab1.queryclient.projections.AvailabilityInterval;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProjectionRepository {
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<FreeRoom> freeRooms = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        System.out.println("Booking added: " + booking);

        for (FreeRoom freeRoom : freeRooms) {
            if (freeRoom.getRoomId().equals(booking.getRoomId())) {
                List<AvailabilityInterval> availabilityIntervals = freeRoom.getAvailabilityIntervals();

                for (AvailabilityInterval availabilityInterval : availabilityIntervals) {
                    if (booking.getStartDate().isAfter(availabilityInterval.getAvailableFrom()) && booking.getEndDate().isBefore(availabilityInterval.getAvailableTo())) {
                        availabilityInterval.setAvailableTo(booking.getStartDate().minusDays(1));
                        availabilityIntervals.add(new AvailabilityInterval(booking.getEndDate().plusDays(1), availabilityInterval.getAvailableTo()));
                    }
                }

                freeRoom.setAvailabilityIntervals(availabilityIntervals);
            }
        }
    }

    public void removeBooking(UUID bookingId) {
        Booking booking = bookings.stream().filter(b -> b.getBookingId().equals(bookingId)).findFirst().orElse(null);
        System.out.println("Booking removed: " + booking);

        for (FreeRoom freeRoom : freeRooms) {
            if (freeRoom.getRoomId().equals(booking.getRoomId())) {
                for (AvailabilityInterval availabilityInterval : freeRoom.getAvailabilityIntervals()) {
                    if (availabilityInterval.getAvailableTo() == booking.getStartDate().minusDays(1)) {
                        availabilityInterval.setAvailableTo(booking.getEndDate());
                    }
                }
            }
        }

        bookings.remove(booking);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer);
    }

    public List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
        return bookings.stream().filter(b -> (
                (b.getStartDate().isAfter(startDate) && b.getStartDate().isBefore(endDate)) ||
                        (b.getEndDate().isAfter(startDate) && b.getEndDate().isBefore(endDate))
        )).toList();
    }

    public List<FreeRoom> getFreeRooms(LocalDate availableFrom, LocalDate availableTo, int numberOfBeds) {
        return freeRooms.stream().filter(r -> {
            List<AvailabilityInterval> availabilityIntervals = r.getAvailabilityIntervals();

            for (AvailabilityInterval a : availabilityIntervals) {
                if (a.getAvailableFrom().isAfter(availableFrom) && a.getAvailableTo().isBefore(availableTo) &&
                        r.getBeds() == numberOfBeds) {
                    return true;
                }
            }

            return false;
        }).toList();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Customer> getCustomersByName(String name) {
        return customers.stream().filter(c -> c.getName().equals(name)).toList();
    }
}
