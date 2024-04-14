package at.fhv.lab1;

import at.fhv.lab1.eventbus.events.RoomBookedEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.eventbus")
public class EventBus {

    public static void main(String[] args) {
        SpringApplication.run(EventBus.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            RoomBookedEvent event = new RoomBookedEvent(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDate.parse("2023-04-05"), LocalDate.parse("2023-04-10"));
            System.out.println(event);
        };
    }
}
