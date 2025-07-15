package com.food.ordering.system.order.service.dataaccess.customer.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity representing a customer in the order service data access layer.
 * 
 * This entity maps to the "order_customer_m_view" materialized view in the
 * "customer" schema,
 * providing a read-only representation of customer data for order processing
 * operations.
 * The entity follows the builder pattern and uses Lombok annotations for
 * boilerplate code generation.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_customer_m_view", schema = "customer")
@Entity
public class CustomerEntity {
    /**
     * The unique identifier for the customer.
     * 
     * This field serves as the primary key and maps to the customer's UUID
     * from the underlying customer service domain.
     */
    @Id
    private UUID id;
}
