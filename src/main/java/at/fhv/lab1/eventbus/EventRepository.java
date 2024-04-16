package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.*;
import org.springframework.stereotype.Component;

import java.io.*;
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

    public void processRoomCreatedEvent(RoomCreatedEvent event) {
        events.add(event);
        writeEventToFile(event);

        publisher.publishRoomCreatedEvent(event);
    }

    private void writeEventToFile(Event event) {
        String filename = "events.dat";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(events);
            System.out.println("Events saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Event> readEvents = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                readEvents = (List<Event>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Events in file:");
        for (Event e : readEvents) {
            System.out.println(e);
        }
    }
}
