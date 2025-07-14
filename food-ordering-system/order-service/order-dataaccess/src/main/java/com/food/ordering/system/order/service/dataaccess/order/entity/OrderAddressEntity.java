package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a delivery address for an order in the food ordering
 * system.
 * This entity maps to the "order_address" table and maintains a one-to-one
 * relationship
 * with the order entity to store delivery address information.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_address")
@Entity
public class OrderAddressEntity {
    /**
     * The unique identifier for the order address.
     */
    @Id
    private UUID id;

    /**
     * The order associated with this delivery address.
     * Uses a one-to-one relationship with cascade operations to manage
     * the order lifecycle along with its address.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    /**
     * The street address for delivery.
     */
    private String street;

    /**
     * The postal code of the delivery address.
     */
    private String postalCode;

    /**
     * The city of the delivery address.
     */
    private String city;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        OrderAddressEntity that = (OrderAddressEntity) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
