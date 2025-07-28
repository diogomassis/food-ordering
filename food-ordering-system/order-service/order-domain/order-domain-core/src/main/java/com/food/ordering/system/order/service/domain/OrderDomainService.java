package com.food.ordering.system.order.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.domain.event.publisher.IDomainEventPublisher;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;

import lombok.extern.slf4j.Slf4j;

import static com.food.ordering.system.domain.DomainConstants.UTC;

/**
 * Service class responsible for handling the domain logic related to orders.
 * <p>
 * This class implements the {@link IOrderDomainService} interface and provides
 * methods for validating and initiating orders, processing payments, approving,
 * and cancelling orders. It ensures that business rules are enforced, such as
 * validating restaurant status and synchronizing product information between
 * the order and the restaurant.
 * <p>
 * All domain events related to order state transitions are created here.
 * Logging is performed for each significant state change.
 */
@Slf4j
public class OrderDomainService implements IOrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant,
            IDomainEventPublisher<OrderCreatedEvent> domainEventPublisher) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), domainEventPublisher);
    }

    @Override
    public OrderPaidEvent payOrder(Order order, IDomainEventPublisher<OrderPaidEvent> domainEventPublisher) {
        order.pay();
        log.info("Order with id {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), domainEventPublisher);
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages,
            IDomainEventPublisher<OrderCancelledEvent> domainEventPublisher) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), domainEventPublisher);
    }

    @Override
    public void cancelOrder(Order order) {
        order.cancel(order.getFailureMessages());
        log.info("Order with id {} is cancelled", order.getId().getValue());
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
