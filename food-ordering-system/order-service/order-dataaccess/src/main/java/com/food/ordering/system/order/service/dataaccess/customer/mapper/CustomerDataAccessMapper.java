package com.food.ordering.system.order.service.dataaccess.customer.mapper;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import com.food.ordering.system.order.service.domain.entity.Customer;

/**
 * Mapper component responsible for converting between CustomerEntity (data
 * access layer)
 * and Customer (domain layer) objects in the order service.
 * 
 * This mapper facilitates the transformation of data between different
 * architectural layers,
 * ensuring proper separation of concerns and maintaining clean architecture
 * principles.
 */
@Component
public class CustomerDataAccessMapper {
    /**
     * Converts a CustomerEntity from the data access layer to a Customer domain
     * entity.
     * 
     * @param customerEntity the customer entity from the database layer
     * @return a Customer domain object with the corresponding customer ID
     */
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
