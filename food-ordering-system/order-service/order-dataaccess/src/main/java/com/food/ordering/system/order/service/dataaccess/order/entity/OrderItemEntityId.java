package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Composite primary key entity for OrderItem.
 * This class represents the composite key used to uniquely identify
 * an order item in the database, consisting of an ID and the associated order.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntityId implements Serializable {
    /**
     * The unique identifier for the order item.
     */
    private Long id;

    /**
     * The order entity that this order item belongs to.
     * This forms part of the composite key along with the id.
     */
    private OrderEntity order;

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        OrderItemEntityId that = (OrderItemEntityId) obj;
        return id.equals(that.id) && order.equals(that.order);
    }
}
