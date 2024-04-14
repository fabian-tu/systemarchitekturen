package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import org.springframework.stereotype.Component;

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
}
