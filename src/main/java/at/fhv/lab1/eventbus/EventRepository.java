package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EventRepository {
  private static final List<Event> events = new ArrayList<>();

  public void addRoomBookedEvent(RoomBookedEvent event) {
    events.add(event);
    writeEventToFile();
  }

  public void addBookingCancelledEvent(BookingCancelledEvent event) {
    events.add(event);
    writeEventToFile();
  }

  public void addCustomerCreatedEvent(CustomerCreatedEvent event) {
    events.add(event);
    writeEventToFile();
  }

  public void addRoomCreatedEvent(RoomCreatedEvent event) {
    events.add(event);
    writeEventToFile();
  }

  public List<Event> getEvents() {
    return events;
  }

  private void writeEventToFile() {
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
