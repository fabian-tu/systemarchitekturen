package at.fhv.lab1.queryclient.rest;

import at.fhv.lab1.queryclient.ProjectionRepository;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import at.fhv.lab1.queryclient.queries.GetBookingsQuery;
import at.fhv.lab1.queryclient.queries.GetCustomersQuery;
import java.time.LocalDate;
import java.util.List;

import at.fhv.lab1.queryclient.queries.GetFreeRoomsQuery;
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
    GetBookingsQuery query = new GetBookingsQuery(startDate, endDate);
    return repository.getBookingsByDate(query.getStartDate(), query.getEndDate());
  }

  @GetMapping(value = "/get-free-rooms", produces = "application/json")
  public List<FreeRoom> getFreeRooms(
      @RequestParam LocalDate availableFrom,
      @RequestParam LocalDate availableTo,
      @RequestParam int numberOfBeds) {
    GetFreeRoomsQuery query = new GetFreeRoomsQuery(availableFrom, availableTo, numberOfBeds);
    return repository.getFreeRooms(
        query.getStartDate(), query.getEndDate(), query.getNumberOfBeds());
  }

  @GetMapping(value = "/get-customers", produces = "application/json")
  public List<Customer> getCustomers(@RequestParam(required = false) String name) {
    if (name != null) {
      GetCustomersQuery query = new GetCustomersQuery(name);
      return repository.getCustomersByName(query.getName());
    }

    return repository.getCustomers();
  }
}
