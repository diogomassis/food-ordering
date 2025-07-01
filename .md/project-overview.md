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

#### Module Structure

The service is configured as a multi-module Maven project to apply the principles of Clean and Hexagonal Architecture. Its structure is as follows:

```folder
food-ordering-system (pom)
└── order-service (pom)
    ├── order-domain (pom)
    │   ├── order-domain-core
    │   └── order-application-service
    ├── order-application
    ├── order-dataaccess
    ├── order-messaging
    └── order-container
```

#### Module Description

* **`food-ordering-system`**: The parent project (root) that manages all global settings and modules. It's packaged as `pom`.
* **`order-service`**: The main module for the ordering service, which groups all its components.
* **`order-domain`**: Contains the core business logic, divided into:
  * **`order-domain-core`**: Entities, value objects, and pure business rules.
  * **`order-application-service`**: Orchestrates domain logic and exposes use cases.
* **`order-application`**: The entry point layer (primary adapter), such as the REST API.
* **`order-dataaccess`**: The persistence layer (secondary adapter) for interacting with the database.
* **`order-messaging`**: The messaging layer (secondary adapter) for communication via Kafka.
* **`order-container`**: Responsible for packaging the application into a runnable JAR and generating the Docker image.

#### Dependencies graph

![Dependencies Graph](../docs/images/dependency-graph.png)

The dependencies graph illustrates the relationships between the modules in the ordering service. To generate this graph, follow these steps:

* Install `graphviz` on your system.

```bash
sudo apt install graphviz -y
```

* Navigate to the `food-ordering-system` directory.

```bash
cd food-ordering-system
```

* Run the following Maven command to generate the dependencies graph:

```bash
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"
```

The generated graph will be saved in the `target` directory as `dependency-graph.png`. You can view it using any image viewer or web browser.

According to the graph, all dependencies are correctly set up.

#### Domain Logic Diagram

![Domain Logic Diagram](../docs/images/order-service-domain-logic-oncourse.png)

This diagram details the business logic contained within the `order-domain-core` module, using Domain-Driven Design concepts to model the order flow.

* **`Aggregates`**: Represent a cluster of domain objects treated as a single unit. The **Order** is the **Aggregate Root**, which ensures the consistency of all operations (like adding items or paying). Other key aggregates include Restaurant and Customer.

* **`Entities`**: These are the core objects with a unique identity, such as `Order` and `OrderItem`.

* **`Value Objects`**: Represent descriptive attributes without a unique identity, such as `OrderId`, `StreetAddress`, and `Money`.

* **`Domain Events`**: These are notifications of business actions that have occurred (e.g., `OrderCreatedEvent`, `OrderPaidEvent`). They are used to communicate changes to other parts of the system in a decoupled manner.
