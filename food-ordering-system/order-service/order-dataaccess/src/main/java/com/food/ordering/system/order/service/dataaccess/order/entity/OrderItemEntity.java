package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an order item in the food ordering system.
 * This entity maps to the "order_items" table and uses a composite primary key
 * consisting of the item ID and the associated order.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderItemEntityId.class)
@Table(name = "order_items")
@Entity
public class OrderItemEntity {
    /**
     * The unique identifier for the order item.
     * Forms part of the composite primary key along with the order.
     */
    @Id
    private Long id;

    /**
     * The order that this item belongs to.
     * Forms part of the composite primary key along with the item ID.
     * Uses cascade operations to manage the order lifecycle.
     */
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    /**
     * The unique identifier of the product associated with this order item.
     */
    private UUID productId;

    /**
     * The price per unit of the product in this order item.
     */
    private BigDecimal price;

    /**
     * The quantity of the product ordered in this item.
     */
    private Integer quantity;

    /**
     * The subtotal for this order item (price * quantity).
     */
    private BigDecimal subTotal;

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
        OrderItemEntity that = (OrderItemEntity) obj;
        return id.equals(that.id) && order.equals(that.order);
    }
}
