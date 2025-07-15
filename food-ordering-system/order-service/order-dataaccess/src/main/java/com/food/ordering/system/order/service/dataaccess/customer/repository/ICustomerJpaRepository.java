package com.food.ordering.system.order.service.dataaccess.customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;

/**
 * JPA repository interface for managing Customer entities in the order service
 * data access layer.
 * Provides CRUD operations and additional query methods for CustomerEntity
 * objects.
 * 
 * This repository extends JpaRepository to inherit standard database operations
 * such as save, find, delete, and update for customer data persistence.
 */
@Repository
public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
