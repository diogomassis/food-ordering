package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RestaurantEntityId implements Serializable {
    private UUID restaurantId;
    private UUID productId;

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
        RestaurantEntityId that = (RestaurantEntityId) obj;
        return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
    }

}
