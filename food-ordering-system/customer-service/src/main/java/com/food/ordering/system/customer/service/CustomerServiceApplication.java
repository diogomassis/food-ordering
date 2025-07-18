package com.food.ordering.system.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Customer Service microservice.
 * <p>
 * This Spring Boot application serves as the entry point for the customer
 * service
 * component of the food ordering system. It handles customer-related operations
 * such as customer registration, profile management, and customer data
 * retrieval.
 * </p>
 */
@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class CustomerServiceApplication {
    /**
     * Main method that serves as the entry point for the Customer Service
     * application.
     * <p>
     * This method bootstraps the Spring Boot application and starts the embedded
     * server to handle incoming requests for customer-related operations.
     * </p>
     * 
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
