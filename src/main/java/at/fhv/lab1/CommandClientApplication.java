package at.fhv.lab1;

import at.fhv.lab1.commandclient.DomainRepository;
import at.fhv.lab1.commandclient.domain.Booking;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.commandclient")
public class CommandClientApplication {
  private final DomainRepository repository;

  public CommandClientApplication(DomainRepository repository) {
    this.repository = repository;
  }

  public static void main(String[] args) {
    SpringApplication.run(CommandClientApplication.class, args);
  }

  @Bean
  public CommandLineRunner run() throws Exception {
    return args -> {
      Room[] rooms = {
        new Room(101, 2, 100.0f),
        new Room(102, 1, 80.0f),
        new Room(103, 3, 120.0f),
        new Room(201, 2, 90.0f),
        new Room(202, 1, 75.0f),
        new Room(203, 4, 150.0f),
        new Room(301, 3, 110.0f),
        new Room(302, 2, 95.0f),
        new Room(303, 2, 100.0f),
        new Room(304, 3, 130.0f)
      };

      System.out.println("\nAdding rooms...");
      for (Room room : rooms) {
        repository.addRoom(room);
        System.out.println(room);
      }

      Customer[] customers = {
        new Customer("John Doe", "123 Main St, Anytown", LocalDate.of(1985, 5, 15)),
        new Customer("Jane Smith", "456 Elm St, Anycity", LocalDate.of(1990, 8, 20)),
        new Customer("Michael Johnson", "789 Oak Ave, Somecity", LocalDate.of(1978, 12, 10)),
        new Customer("Emily Brown", "321 Pine Rd, Othercity", LocalDate.of(1988, 3, 25)),
        new Customer("David Lee", "654 Cedar Blvd, Townsville", LocalDate.of(1995, 9, 5)),
        new Customer("Sara White", "987 Maple Dr, Villageville", LocalDate.of(1982, 7, 17)),
        new Customer("Tom Jones", "111 Willow Ln, Countryside", LocalDate.of(1970, 4, 30)),
        new Customer("Lucy Green", "222 Birch Ave, Riverside", LocalDate.of(1993, 6, 12)),
        new Customer("Chris Taylor", "333 Ash St, Hilltown", LocalDate.of(1987, 10, 8)),
        new Customer("Olivia Clark", "555 Pine St, Mountain City", LocalDate.of(1991, 2, 3))
      };

      System.out.println("\nAdding customers...");
      for (Customer customer : customers) {
        repository.addCustomer(customer);
        System.out.println(customer);
      }

      Booking[] bookings = {
        new Booking(
            customers[0].getCustomerId(),
            rooms[0].getRoomId(),
            LocalDate.of(2024, 9, 15),
            LocalDate.of(2024, 9, 20)),
        new Booking(
            customers[1].getCustomerId(),
            rooms[1].getRoomId(),
            LocalDate.of(2024, 5, 18),
            LocalDate.of(2024, 5, 22)),
        new Booking(
            customers[2].getCustomerId(),
            rooms[2].getRoomId(),
            LocalDate.of(2024, 2, 5),
            LocalDate.of(2024, 2, 12)),
        new Booking(
            customers[3].getCustomerId(),
            rooms[3].getRoomId(),
            LocalDate.of(2024, 4, 22),
            LocalDate.of(2024, 4, 23)),
        new Booking(
            customers[4].getCustomerId(),
            rooms[4].getRoomId(),
            LocalDate.of(2024, 8, 24),
            LocalDate.of(2024, 9, 1)),
        new Booking(
            customers[5].getCustomerId(),
            rooms[5].getRoomId(),
            LocalDate.of(2024, 7, 30),
            LocalDate.of(2024, 8, 12)),
        new Booking(
            customers[6].getCustomerId(),
            rooms[6].getRoomId(),
            LocalDate.of(2024, 3, 28),
            LocalDate.of(2024, 4, 5)),
        new Booking(
            customers[7].getCustomerId(),
            rooms[7].getRoomId(),
            LocalDate.of(2024, 12, 12),
            LocalDate.of(2024, 12, 17)),
        new Booking(
            customers[8].getCustomerId(),
            rooms[8].getRoomId(),
            LocalDate.of(2024, 5, 2),
            LocalDate.of(2024, 5, 9)),
        new Booking(
            customers[9].getCustomerId(),
            rooms[9].getRoomId(),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 15))
      };

      System.out.println("\nAdding bookings...");
      for (Booking booking : bookings) {
        repository.addBooking(booking);
        System.out.println(booking);
      }
    };
  }
}
