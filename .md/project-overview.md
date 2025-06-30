# Food Ordering System

This project describes a food ordering system made up of several services.

The ordering service manages customer requests and interacts with a sagas coordinator via Kafka to ensure consistent operations. The payment service processes financial transactions, while the restaurant service manages order approval. A customer service provides customer data and there is also a materialized visualization system for queries.

All of this is orchestrated to guarantee communication and data integrity through messaging and local databases.

## Architecture Overview

![Architecture Overview](../docs/images/project-overview-section-1.png)

A distributed food ordering system that manages the flow of orders, payments and approvals for restaurants, guaranteeing consistency and resilience in operations.

## Services

### Order Services

![Order Service](../docs/images/order-service-hexagonal-section-2-share.png)

This diagram shows a hexagonal architecture for an ordering service, emphasizing the independence of the central domain from external details, the inversion of dependencies and the ease of replacing secondary adapters.
