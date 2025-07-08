package com.food.ordering.system.order.service.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.ICustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.IOrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.IRestaurantRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Helper class responsible for handling the creation and persistence of orders.
 * <p>
 * This class coordinates the validation of customer and restaurant existence,
 * maps incoming order commands to domain entities, and persists the order.
 * It also triggers the domain service to validate and initiate the order
 * process.
 * </p>
 *
 * @author
 */
@Slf4j
@Component
public class OrderCreateHelper {
    /**
     * Service for order domain logic and validation.
     */
    private final OrderDomainService orderDomainService;

    /**
     * Repository for persisting and retrieving orders.
     */
    private final IOrderRepository orderRepository;

    /**
     * Repository for retrieving customer information.
     */
    private final ICustomerRepository customerRepository;

    /**
     * Repository for retrieving restaurant information.
     */
    private final IRestaurantRepository restaurantRepository;

    /**
     * Mapper for converting between data transfer objects and domain entities.
     */
    private final OrderDataMapper orderDataMapper;

    /**
     * Constructs an {@code OrderCreateHelper} with required dependencies.
     *
     * @param orderDomainService   the domain service for order logic
     * @param orderRepository      the repository for order persistence
     * @param customerRepository   the repository for customer retrieval
     * @param restaurantRepository the repository for restaurant retrieval
     * @param orderDataMapper      the mapper for DTO and entity conversion
     */
    public OrderCreateHelper(OrderDomainService orderDomainService, IOrderRepository orderRepository,
            ICustomerRepository customerRepository, IRestaurantRepository restaurantRepository,
            OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    /**
     * Validates and persists a new order based on the provided command.
     * <p>
     * This method checks the existence of the customer and restaurant,
     * maps the command to an order entity, validates and initiates the order,
     * and persists it to the repository.
     * </p>
     *
     * @param createOrderCommand the command containing order details
     * @return the event representing the created order
     * @throws OrderDomainException if validation or persistence fails
     */
    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id {}", orderCreatedEvent.getOrder().getId().getValue().toString());
        return orderCreatedEvent;
    }

    /**
     * Validates the existence of the restaurant specified in the order command.
     *
     * @param createOrderCommand the command containing restaurant information
     * @return the found {@link Restaurant} entity
     * @throws OrderDomainException if the restaurant is not found
     */
    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id {}",
                    createOrderCommand.getRestaurantId().toString());
            throw new OrderDomainException(
                    "Could not find restaurant with restaurant id " + createOrderCommand.getRestaurantId().toString());
        }
        return optionalRestaurant.get();
    }

    /**
     * Validates the existence of the customer by ID.
     *
     * @param customerId the UUID of the customer to check
     * @throws OrderDomainException if the customer is not found
     */
    private void checkCustomer(UUID customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomer(customerId);
        if (optionalCustomer.isEmpty()) {
            log.warn("Could not find customer with customer id {}", customerId.toString());
            throw new OrderDomainException("Could not find customer with customer id " + customerId.toString());
        }
    }

    /**
     * Persists the given order entity.
     *
     * @param order the {@link Order} entity to save
     * @return the saved {@link Order} entity
     * @throws OrderDomainException if the order could not be saved
     */
    private Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        if (savedOrder == null) {
            log.warn("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id {}", savedOrder.getId().getValue().toString());
        return savedOrder;
    }
}
