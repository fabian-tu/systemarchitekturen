package at.fhv.lab1.commandclient.commands;

import at.fhv.lab1.eventbus.events.Event;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class GetAllEventsCommand {
    private final WebClient eventBusClient;

    public GetAllEventsCommand(WebClient eventBusClient) {
        this.eventBusClient = eventBusClient;
    }

    public List<Event> execute() {
        return eventBusClient.get()
                .uri("/events")
                .retrieve()
                .bodyToFlux(Event.class)
                .collectList()
                .block();
    }
}
