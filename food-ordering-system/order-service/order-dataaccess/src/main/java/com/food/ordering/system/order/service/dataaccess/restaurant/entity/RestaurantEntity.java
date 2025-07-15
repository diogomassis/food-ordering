package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class RestaurantEntity {
    @Id
    private UUID restaurantId;

    @Id
    private UUID productId;

    private String restaurantName;
    private String restaurantActive;
    private String productName;
    private String productPrice;

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        RestaurantEntity that = (RestaurantEntity) obj;
        return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
    }
}
