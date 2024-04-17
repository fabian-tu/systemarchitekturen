package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DomainRepository {
  private final ArrayList<Booking> bookings = new ArrayList<>();
  private final ArrayList<Customer> customers = new ArrayList<>();
  private final ArrayList<Room> rooms = new ArrayList<>();

  public void addBooking(Booking booking) {
    bookings.add(booking);
    System.out.println("Booking added: " + booking);
  }

  public void removeBooking(Booking booking) {
    bookings.remove(booking);
    System.out.println("Booking removed: " + booking);
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
    System.out.println("Customer added: " + customer);
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void addRoom(Room room) {
    rooms.add(room);
    System.out.println("Room added: " + room);
  }

  public List<Room> getRooms() {
    return rooms;
  }
}
