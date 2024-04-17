package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.queryclient.ProjectionRepository;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueryRestController {
  private final ProjectionRepository repository;

  public QueryRestController(ProjectionRepository repository) {
    this.repository = repository;
  }

  @GetMapping(value = "/get-bookings", produces = "application/json")
  public List<Booking> getBookings(
      @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
    return repository.getBookingsByDate(startDate, endDate);
  }

  @GetMapping(value = "/get-free-rooms", produces = "application/json")
  public List<FreeRoom> getFreeRooms(
      @RequestParam LocalDate availableFrom,
      @RequestParam LocalDate availableTo,
      @RequestParam int numberOfBeds) {
    return repository.getFreeRooms(availableFrom, availableTo, numberOfBeds);
  }

  @GetMapping(value = "/get-customers", produces = "application/json")
  public List<Customer> getCustomers(@RequestParam(required = false) String name) {
    if (name != null) {
      return repository.getCustomersByName(name);
    }

    return repository.getCustomers();
  }
}
