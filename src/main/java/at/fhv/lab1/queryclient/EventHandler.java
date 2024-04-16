package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import at.fhv.lab1.queryclient.projections.AvailabilityInterval;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventHandler {
    private final ProjectionRepository repository;

    public EventHandler(ProjectionRepository repository) {
        this.repository = repository;
    }

    public void handleRoomBookedEvent(RoomBookedEvent event) {
        Booking booking = new Booking(event.getBookingId(), event.getCustomerId(), event.getRoomId(), event.getStartDate(), event.getEndDate());

        repository.addBooking(booking);
    }

    public void handleBookingCancelledEvent(BookingCancelledEvent event) {
        repository.removeBooking(event.getBookingId());
    }

    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        Customer customer = new Customer(event.getCustomerId(), event.getName(), event.getAddress(), event.getDateOfBirth());

        repository.addCustomer(customer);
    }

    public void handleRoomCreatedEvent(RoomCreatedEvent event) {
        List<AvailabilityInterval> availabilityIntervals = new ArrayList<>();
        availabilityIntervals.add(new AvailabilityInterval(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)));

        FreeRoom room = new FreeRoom(event.getRoomId(), event.getRoomNumber(), event.getBeds(), event.getPricePerNight(), availabilityIntervals);

        repository.addRoom(room);
    }
}
