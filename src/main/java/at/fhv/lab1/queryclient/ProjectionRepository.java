package at.fhv.lab1.queryclient;

import at.fhv.lab1.queryclient.projections.AvailabilityInterval;
import at.fhv.lab1.queryclient.projections.Booking;
import at.fhv.lab1.queryclient.projections.Customer;
import at.fhv.lab1.queryclient.projections.FreeRoom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ProjectionRepository {
  private final List<Booking> bookings = new ArrayList<>();
  private final List<Customer> customers = new ArrayList<>();
  private final List<FreeRoom> freeRooms = new ArrayList<>();

  public void addBooking(Booking booking) {
    bookings.add(booking);
    System.out.println("Booking added: " + booking);

    for (FreeRoom room : freeRooms) {
      if (room.getRoomId().equals(booking.getRoomId())) {
        List<AvailabilityInterval> intervals = room.getAvailabilityIntervals();

        for (int j = 0; j < intervals.size(); j++) {
          AvailabilityInterval interval = intervals.get(j);

          if (!interval.getAvailableFrom().isAfter(booking.getStartDate())
              && !interval.getAvailableTo().isBefore(booking.getEndDate())) {
            LocalDate oldTo = interval.getAvailableTo();

            AvailabilityInterval newInterval =
                new AvailabilityInterval(booking.getEndDate().plusDays(1), oldTo);

            interval.setAvailableTo(booking.getStartDate().minusDays(1));
            intervals.add(j + 1, newInterval);

            return;
          }
        }
        return;
      }
    }
  }

  public void removeBooking(UUID bookingId) {
    Booking booking =
        bookings.stream().filter(b -> b.getBookingId().equals(bookingId)).findFirst().orElse(null);
    System.out.println("Booking removed: " + booking);

    for (FreeRoom freeRoom : freeRooms) {
      if (freeRoom.getRoomId().equals(booking.getRoomId())) {
        List<AvailabilityInterval> intervals = freeRoom.getAvailabilityIntervals();

        for (int j = 0; j < intervals.size(); j++) {
          AvailabilityInterval currentInterval = intervals.get(j);
          LocalDate currentAvailableTo = currentInterval.getAvailableTo();

          if (currentAvailableTo.equals(booking.getStartDate().minusDays(1))) {
            if (j + 1 < intervals.size()) {
              AvailabilityInterval nextInterval = intervals.get(j + 1);

              if (booking.getEndDate().equals(nextInterval.getAvailableFrom().minusDays(1))) {
                currentInterval.setAvailableTo(nextInterval.getAvailableTo());
                intervals.remove(nextInterval);
              } else {
                currentInterval.setAvailableTo(booking.getEndDate());
              }

              break;
            }
          }
        }

        break;
      }
    }

    bookings.remove(booking);
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
    System.out.println("Customer added: " + customer);
  }

  public List<Booking> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
    return bookings.stream()
        .filter(
            b ->
                ((!b.getStartDate().isBefore(startDate) && !b.getStartDate().isAfter(endDate))
                    || (!b.getEndDate().isBefore(startDate) && !b.getEndDate().isAfter(endDate))))
        .toList();
  }

  public List<FreeRoom> getFreeRooms(
      LocalDate availableFrom, LocalDate availableTo, int numberOfBeds) {
    System.out.println("Free Rooms: " + freeRooms);
    return freeRooms.stream()
        .filter(
            r -> {
              List<AvailabilityInterval> availabilityIntervals = r.getAvailabilityIntervals();

              for (AvailabilityInterval a : availabilityIntervals) {
                if (!a.getAvailableFrom().isAfter(availableFrom)
                    && !a.getAvailableTo().isBefore(availableTo)
                    && r.getBeds() == numberOfBeds) {
                  return true;
                }
              }

              return false;
            })
        .toList();
  }

  public void addRoom(FreeRoom room) {
    freeRooms.add(room);
    System.out.println("Room added: " + room);
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public List<Customer> getCustomersByName(String name) {
    return customers.stream().filter(c -> c.getName().equals(name)).toList();
  }

  public void deleteQueryModels() {
    bookings.clear();
    customers.clear();
    freeRooms.clear();
  }
}
