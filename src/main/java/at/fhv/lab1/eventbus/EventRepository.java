package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.Event;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {
    private final EventPublisher publisher;
    private final List<Event> events = new ArrayList<>();

    public EventRepository(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void processRoomBookedEvent(RoomBookedEvent event) {
        events.add(event);
        writeEventToFile(event);

        publisher.publishRoomBookedEvent(event);
    }

    public void processBookingCancelledEvent(BookingCancelledEvent event) {
        events.add(event);
        writeEventToFile(event);

        publisher.publishBookingCancelledEvent(event);
    }

    public void processCustomerCreatedEvent(CustomerCreatedEvent event) {
        events.add(event);
        writeEventToFile(event);

        publisher.publishCustomerCreatedEvent(event);
    }

    private void writeEventToFile(Event event) {
        String filename = "events.txt";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(event);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("An error occurred while appending objects to the file.");
            e.printStackTrace();
        }
    }
}
