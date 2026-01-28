# Grid Robots Simulator

### Introduction
This project is a multi-robot grid simulator. The domain consists of a rectangular grid where multiple robots can be placed and moved following a set of instructions. Each robot has an initial position (x, y) and a direction (North, East, South, West). The simulator processes sequential commands for each robot:
- **L**: Turn Left 90 degrees.
- **R**: Turn Right 90 degrees.
- **M**: Move forward one grid point in the current direction.

A key feature of this simulator is that robots move sequentially. Once a robot finishes its movement, its final position is treated as a dynamic obstacle for subsequent robots, preventing collisions.

### Architecture Approach
The system needs to receive a few lines of text and return a short response. The expected load is moderate, focusing on simplicity, maintainability, and the potential to scale if traffic increases.  
Below are several architectural alternatives, ordered from **least to most scalable**, followed by the chosen solution.

#### 1. Local Script or Command-Line Application
A basic solution would be a script or console application that processes the text locally.
- **Advantages:** Extremely simple, no external dependencies or deployment needed.
- **Disadvantages:** Not remotely accessible, no concurrency, no scalability.
- **Scalability:** Very low — limited to local or testing environments.

#### 2. Monolithic Application with an HTTP Endpoint
In this approach, the service is exposed through an HTTP endpoint integrated into a monolith (for instance, a servlet or Spring Boot controller).
- **Advantages:** Easy to implement and integrate via HTTP.
- **Disadvantages:** Strong coupling, shared deployment cycle, limited scalability.
- **Scalability:** Low to medium, suitable for small traffic volumes.

#### 3. Lightweight REST Service (Chosen Option)
A standalone REST service exposing an HTTP endpoint that accepts plain text input and returns a text response.
- **Advantages:**
    - Standard pattern, simple to integrate and document (OpenAPI/Swagger).
    - Compatible with all clients (browser, mobile, backend systems).
    - Container-friendly, easy to scale horizontally.
    - Balanced in simplicity and extensibility.
- **Disadvantages:** Synchronous communication model; if processing time grows, async patterns may be required.
- **Scalability:** Medium-high — ideal for moderate traffic and low-complexity exchanges.

#### 4. gRPC or GraphQL Service
Modern alternatives focused on performance and flexibility.
- **gRPC:** Binary protocol with Protobuf contracts — very efficient for internal microservice communication.
- **GraphQL:** Flexible schema, ideal for complex data queries or client-driven APIs.
- **Advantages:** High throughput, well-suited for distributed architectures.
- **Disadvantages:** Increased complexity in setup, deployment, and client integration.
- **Scalability:** High — suitable for large systems and multiple consumers.

#### 5. Messaging-Based Service (Kafka, SQS, RabbitMQ)
For heavy load or asynchronous scenarios, messaging can decouple request and response processing, e.g., using **Kafka**.
- **Design:** The client publishes the text to an input topic; a consumer processes it and writes the response to another topic or store for retrieval.
- **Advantages:**
    - Highly scalable and resilient.
    - Full decoupling between producers and consumers.
    - Excellent for high-volume or batch-processing scenarios.
- **Disadvantages:**
    - Greater operational complexity (Kafka infrastructure, partition and offset management).
    - Not suitable for low-latency, real-time responses.
- **Scalability:** Very high — built for distributed, parallel processing.

#### Conclusion
After evaluating all options, a **lightweight RESTful service** provides the best balance between *simplicity, maintainability, and scalability*.  
It ensures straightforward, synchronous communication and can easily evolve into a more scalable or asynchronous architecture by adding **Kafka-based decoupling** in future phases.

Furthermore, since the goal of this project is to **demonstrate my proficiency in building a small REST web service using Spring Boot and a hexagonal architecture and DDD**, this approach was selected even though alternative technologies could make the microservice more optimal from a pure performance or scalability standpoint.

As this project does not **persist any data**, no specific database implementation has been included. However, for **performance analysis** and **monitoring** purposes, it would be valuable to record logs capturing processing and response times, and enable **Elastic/Kibana ingestion** for later analysis or potentially for a **24/7 alerting service**.

Finally, although the project follows a **Test-Driven Development (TDD)** approach, a **Cucumber (BDD)** test has also been added to showcase familiarity and experience with behavior-driven testing methodologies.


### Technologies
The project is built using the following technologies:
- **Java 21**: The core programming language.
- **Spring Boot 3.4.2**: The framework used for building the REST API.
- **Maven**: Dependency management and build tool.
- **SpringDoc OpenAPI (Swagger UI)**: For API documentation and testing.
- **Lombok**: To reduce boilerplate code.
- **Cucumber**: For Behavior-Driven Development (BDD) testing.
- **JUnit 5**: For unit and integration testing.
- **OpenAPI Generator**: To generate DTOs and API interfaces from a YAML specification.

### How to Build and Run

#### Compile
To compile the project and generate the necessary sources (OpenAPI DTOs), run:
```bash
mvn clean compile
```

#### Run Tests
To execute all tests (including Unit tests and Cucumber scenarios), run:
```bash
mvn test
```

#### Execute via Maven
To start the application using the Spring Boot Maven plugin:
```bash
mvn spring-boot:run
```

#### Accessing Swagger UI
Once the application is running, you can access the Swagger UI to interact with the API at:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

The main endpoint for the simulation is `POST /api/robots`, which accepts a `text/plain` input and returns the final positions of the robots in the same format.
