package com.food.ordering.system.order.service.dataaccess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.customer.repository.ICustomerJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.ports.output.repository.ICustomerRepository;

/**
 * Repository adapter implementation for customer data access operations in the
 * order service.
 * 
 * This class serves as an adapter between the domain layer's repository
 * interface
 * and the data access layer's JPA repository, implementing the hexagonal
 * architecture pattern.
 * It handles the conversion between domain entities and data access entities
 * using
 * the CustomerDataAccessMapper.
 */
@Component
public class CustomerRepository implements ICustomerRepository {
    /**
     * JPA repository for performing database operations on CustomerEntity objects.
     */
    private final ICustomerJpaRepository customerJpaRepository;

    /**
     * Mapper for converting between CustomerEntity (data layer) and Customer
     * (domain layer) objects.
     */
    private final CustomerDataAccessMapper customerDataAccessMapper;

    /**
     * Constructs a new CustomerRepository with the required dependencies.
     * 
     * @param customerJpaRepository    the JPA repository for customer data
     *                                 operations
     * @param customerDataAccessMapper the mapper for entity conversions between
     *                                 layers
     */
    public CustomerRepository(ICustomerJpaRepository customerJpaRepository,
            CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
