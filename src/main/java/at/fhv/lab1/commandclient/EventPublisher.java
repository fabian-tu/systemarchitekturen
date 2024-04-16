package at.fhv.lab1.commandclient;

import at.fhv.lab1.eventbus.events.BookingCancelledEvent;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import at.fhv.lab1.eventbus.events.RoomCreatedEvent;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher {
    private final WebClient localApiClient = WebClient.create("http://localhost:8082");

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

        System.out.println("Event published: " + event);
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

        System.out.println("Event published: " + event);
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

        System.out.println("Event published: " + event);
    }

    public void publishRoomCreatedEvent(RoomCreatedEvent event) {
        localApiClient
                .post()
                .uri("/room-created-event")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event), RoomCreatedEvent.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        System.out.println("Event published: " + event);
    }
}
