package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher {
    private final WebClient localApiClient = WebClient.create("http://localhost:8083");

    public void publishRoomBookedEvent(RoomBookedEvent event) {
        localApiClient
                .post()
                .uri("/room-booked-event")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), RoomBookedEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public void publishBookingCancelledEvent(BookingCancelledEvent event) {
        localApiClient
                .post()
                .uri("/booking-cancelled-event")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), BookingCancelledEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public void publishCustomerCreatedEvent(CustomerCreatedEvent event) {
        localApiClient
                .post()
                .uri("/customer-created-event")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), CustomerCreatedEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
