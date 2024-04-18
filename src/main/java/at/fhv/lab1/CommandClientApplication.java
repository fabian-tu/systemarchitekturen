package at.fhv.lab1;

import at.fhv.lab1.commandclient.CommandHandler;
import at.fhv.lab1.commandclient.DomainRepository;
import at.fhv.lab1.commandclient.commands.BookRoomCommand;
import at.fhv.lab1.commandclient.commands.CancelBookingCommand;
import at.fhv.lab1.commandclient.commands.CreateCustomerCommand;
import at.fhv.lab1.commandclient.commands.CreateRoomCommand;
import at.fhv.lab1.commandclient.domain.Customer;
import at.fhv.lab1.commandclient.domain.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.commandclient")
public class CommandClientApplication {
  private final CommandHandler handler;
  private final DomainRepository repository;

  private final WebClient eventBus = WebClient.create("http://localhost:8082");
  private final WebClient queryClient = WebClient.create("http://localhost:8083");

  public CommandClientApplication(CommandHandler handler, DomainRepository repository) {
    this.handler = handler;
    this.repository = repository;
  }

  public static void main(String[] args) {
    SpringApplication.run(CommandClientApplication.class, args);
  }

  private static List<String> parseArguments(String input) {
    List<String> arguments = new ArrayList<>();
    Matcher matcher = Pattern.compile("\"([^\"]*)\"|\\S+").matcher(input);

    while (matcher.find()) {
      if (matcher.group(1) != null) {
        // Quoted argument found
        arguments.add(matcher.group(1));
      } else {
        // Unquoted argument found
        arguments.add(matcher.group());
      }
    }

    return arguments;
  }

  @Bean
  public CommandLineRunner run() throws Exception {
    return args -> {
      Scanner scanner = new Scanner(System.in);

      while (true) {
        System.out.print("Enter command: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
          System.out.println("Exiting CLI. Goodbye!");
          break;
        }

        List<String> arguments = parseArguments(input);
        String command = arguments.remove(0);

        switch (command) {
          case "load-test-data":
            loadTestData();
            break;
          case "book-room":
            handler.handleBookRoomCommand(
                new BookRoomCommand(
                    UUID.fromString(arguments.get(0)),
                    UUID.fromString(arguments.get(1)),
                    LocalDate.parse(arguments.get(2)),
                    LocalDate.parse(arguments.get(3))));
            break;
          case "cancel-booking":
            handler.handleCancelBookingCommand(
                new CancelBookingCommand(UUID.fromString(arguments.get(0))));
            break;
          case "create-customer":
            handler.handleCreateCustomerCommand(
                new CreateCustomerCommand(
                    arguments.get(0), arguments.get(1), LocalDate.parse(arguments.get(2))));
            break;
          case "create-room":
            handler.handleCreateRoomCommand(
                new CreateRoomCommand(
                    Integer.parseInt(arguments.get(0)),
                    Integer.parseInt(arguments.get(1)),
                    Float.parseFloat(arguments.get(2))));
            break;
          case "delete-query-models":
            queryClient
                .post()
                .uri("/delete-query-models")
                .retrieve()
                .bodyToMono(Void.class)
                .block();

            System.out.println("Query models deleted.");
            break;
          case "restore-query-models":
            eventBus.post().uri("/restore-query-models").retrieve().bodyToMono(Void.class).block();

            System.out.println("Query models restored.");
            break;
          case "get-events":
            List<Object> events =
                eventBus
                    .get()
                    .uri("/get-events")
                    .retrieve()
                    .bodyToFlux(Object.class)
                    .collectList()
                    .block();

            for (Object event : events) {
              System.out.println(event);
            }

            break;
          default:
            System.out.println("Invalid command. Please try again.");
        }
      }

      scanner.close();
    };
  }

  private void loadTestData() {
    List<CreateRoomCommand> createRoomCommands = new ArrayList<>();
    createRoomCommands.add(new CreateRoomCommand(101, 2, 100.0f));
    createRoomCommands.add(new CreateRoomCommand(102, 1, 80.0f));
    createRoomCommands.add(new CreateRoomCommand(103, 3, 120.0f));
    createRoomCommands.add(new CreateRoomCommand(201, 2, 90.0f));
    createRoomCommands.add(new CreateRoomCommand(202, 1, 75.0f));
    createRoomCommands.add(new CreateRoomCommand(203, 4, 150.0f));
    createRoomCommands.add(new CreateRoomCommand(301, 3, 110.0f));
    createRoomCommands.add(new CreateRoomCommand(302, 2, 95.0f));
    createRoomCommands.add(new CreateRoomCommand(303, 2, 100.0f));
    createRoomCommands.add(new CreateRoomCommand(304, 3, 130.0f));

    System.out.println("\nAdding rooms...");
    for (CreateRoomCommand createRoomCommand : createRoomCommands) {
      handler.handleCreateRoomCommand(createRoomCommand);
    }

    List<CreateCustomerCommand> createCustomerCommands = new ArrayList<>();
    createCustomerCommands.add(
        new CreateCustomerCommand("John Doe", "123 Main St, Anytown", LocalDate.of(1985, 5, 15)));
    createCustomerCommands.add(
        new CreateCustomerCommand("Jane Smith", "456 Elm St, Anycity", LocalDate.of(1990, 8, 20)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Michael Johnson", "789 Oak Ave, Somecity", LocalDate.of(1978, 12, 10)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Emily Brown", "321 Pine Rd, Othercity", LocalDate.of(1988, 3, 25)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "David Lee", "654 Cedar Blvd, Townsville", LocalDate.of(1995, 9, 5)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Sara White", "987 Maple Dr, Villageville", LocalDate.of(1982, 7, 17)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Tom Jones", "111 Willow Ln, Countryside", LocalDate.of(1970, 4, 30)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Lucy Green", "222 Birch Ave, Riverside", LocalDate.of(1993, 6, 12)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Chris Taylor", "333 Ash St, Hilltown", LocalDate.of(1987, 10, 8)));
    createCustomerCommands.add(
        new CreateCustomerCommand(
            "Olivia Clark", "555 Pine St, Mountain City", LocalDate.of(1991, 2, 3)));

    System.out.println("\nAdding customers...");
    for (CreateCustomerCommand createCustomerCommand : createCustomerCommands) {
      handler.handleCreateCustomerCommand(createCustomerCommand);
    }

    List<Customer> customers = repository.getCustomers();
    List<Room> rooms = repository.getRooms();

    List<BookRoomCommand> bookRoomCommands = new ArrayList<>();
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(0).getCustomerId(),
            rooms.get(0).getRoomId(),
            LocalDate.of(2024, 9, 15),
            LocalDate.of(2024, 9, 20)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(1).getCustomerId(),
            rooms.get(1).getRoomId(),
            LocalDate.of(2024, 5, 18),
            LocalDate.of(2024, 5, 22)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(2).getCustomerId(),
            rooms.get(2).getRoomId(),
            LocalDate.of(2024, 2, 5),
            LocalDate.of(2024, 2, 12)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(3).getCustomerId(),
            rooms.get(3).getRoomId(),
            LocalDate.of(2024, 4, 22),
            LocalDate.of(2024, 4, 23)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(4).getCustomerId(),
            rooms.get(4).getRoomId(),
            LocalDate.of(2024, 8, 24),
            LocalDate.of(2024, 9, 1)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(5).getCustomerId(),
            rooms.get(5).getRoomId(),
            LocalDate.of(2024, 7, 30),
            LocalDate.of(2024, 8, 12)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(6).getCustomerId(),
            rooms.get(6).getRoomId(),
            LocalDate.of(2024, 3, 28),
            LocalDate.of(2024, 4, 5)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(7).getCustomerId(),
            rooms.get(7).getRoomId(),
            LocalDate.of(2024, 12, 12),
            LocalDate.of(2024, 12, 17)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(8).getCustomerId(),
            rooms.get(8).getRoomId(),
            LocalDate.of(2024, 5, 2),
            LocalDate.of(2024, 5, 9)));
    bookRoomCommands.add(
        new BookRoomCommand(
            customers.get(9).getCustomerId(),
            rooms.get(9).getRoomId(),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 15)));

    System.out.println("\nAdding bookings...");
    for (BookRoomCommand bookRoomCommand : bookRoomCommands) {
      handler.handleBookRoomCommand(bookRoomCommand);
    }
  }
}
