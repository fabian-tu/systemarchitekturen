package at.fhv.lab1;

import at.fhv.lab1.queryclient.ProjectionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.queryclient")
public class QueryClientApplication {
    private final ProjectionRepository repository;

    public QueryClientApplication(ProjectionRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(QueryClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            
        };
    }
}
