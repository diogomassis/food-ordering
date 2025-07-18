package com.food.ordering.system.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for the Order Service.
 * Configures JPA repositories and entity scanning.
 */
@EnableJpaRepositories(basePackages = "com.food.ordering.system.order.service.dataaccess")
@EntityScan(basePackages = "com.food.ordering.system.order.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderServiceApplication {
    /**
     * Entry point for the Order Service Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
