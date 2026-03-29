package br.com.cqrstraining;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class QueryApplication {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logMongoConfiguration() {
        log.info("Mongo URI em runtime: {}", mongoUri);
    }
}
