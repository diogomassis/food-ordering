package com.food.ordering.system.order.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainService implements IOrderDomainService {
private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payOrder'");
    }

    @Override
    public void approveOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'approveOrder'");
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrderPayment'");
    }

    @Override
    public void cancelOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    /**
     * Validates if the given restaurant is active.
     * <p>
     * Throws an {@link OrderDomainException} if the restaurant is not active.
     *
     * @param restaurant the {@link Restaurant} to validate
     * @throws OrderDomainException if the restaurant is not active
     */
    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException(
                    "Restaurant with id " + restaurant.getId().getValue() + " is currently not active!");
        }
    }

    /**
     * Updates each {@link Product} in the order with the confirmed name and price
     * from the corresponding product in the restaurant.
     * <p>
     * This ensures that the order uses the latest product information from the
     * restaurant.
     *
     * @param order      the {@link Order} containing the items to update
     * @param restaurant the {@link Restaurant} providing the confirmed product data
     */
    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(restaurantProduct)) {
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                        restaurantProduct.getPrice());
            }
        }));
    }
}
