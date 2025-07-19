package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an order in the food ordering system.
 * This entity maps to the "orders" table and serves as the root aggregate
 * for order-related data including order items and delivery address.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders", schema = "\"order\"")
@Entity
public class OrderEntity {
    /**
     * The unique identifier for the order.
     */
    @Id
    private UUID id;

    /**
     * The unique identifier of the customer who placed the order.
     */
    private UUID customerId;

    /**
     * The unique identifier of the restaurant from which the order is placed.
     */
    private UUID restaurantId;

    /**
     * The unique tracking identifier for monitoring the order status.
     */
    private UUID trackingId;

    /**
     * The total price of the order.
     */
    private BigDecimal price;

    /**
     * The current status of the order (e.g., PENDING, APPROVED, CANCELLED).
     * Stored as string representation in the database.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * Error messages or failure reasons if the order processing fails.
     */
    private String failureMessages;

    /**
     * The delivery address associated with this order.
     * Uses a one-to-one relationship with cascade operations.
     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;

    /**
     * The list of items included in this order.
     * Uses a one-to-many relationship with cascade operations.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        OrderEntity that = (OrderEntity) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
