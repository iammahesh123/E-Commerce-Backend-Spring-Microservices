# eCommerce Microservices Project
Welcome to our eCommerce microservices project repository! This project aims to provide a scalable and modular solution for building an eCommerce platform using microservices architecture. Each microservice handles specific functionalities, such as user management, product catalog, order processing, and payment processing.
## Introduction
In the modern era of eCommerce, building scalable and efficient systems is crucial to meet the demands of customers and handle high traffic loads. Our project employs a microservices architecture, which allows for greater flexibility, scalability, and resilience compared to traditional monolithic architectures.

This repository contains the source code and configurations for each microservice in our eCommerce platform. Each microservice is independently deployable and communicates with others via lightweight protocols such as HTTP or messaging queues.
## Technologies Used
- `Spring Boot`: Framework for creating microservices in Java.
- `Spring Cloud`: Provides tools and libraries for building microservices architecture.
- `Kafka`: Distributed streaming platform for building real-time data pipelines.
- `MySQL`: Open-source relational database for storing application data.
- `RESTful APIs`: Communication protocol for inter-microservice communication.
- `Git`: Version control system for managing source code.
## Microservices Overview
Our eCommerce platform consists of the following microservices:
1. `User Service`: Handles user registration, authentication, and profile management.
2. `Product Service`: Manages product catalog, inventory, and product information.
3. `Order Service`: Processes and manages orders placed by customers.
4. `Payment Service`: Handles payment processing and integration with payment gateways.
## Entity-Relationship Diagram (ERD)
![ERD](https://github.com/iammahesh123/E-Commerce-Backend-Spring-Microservices/blob/master/EntityFinal.jpg)
## Note: 
This README file focuses on the `User Service` microservice. 
## User Service
The User Service microservice is responsible for managing user-related functionalities within our eCommerce platform. It provides endpoints for user registration, authentication, profile management, and user information retrieval.
### Features
- `User registration`: Allows users to register and create new accounts.
- `User authentication`: Supports user authentication using credentials.
- `Profile management`: Enables users to update their profile information.
- `User information retrieval`: Provides endpoints for retrieving user details.
### Endpoints
#### User Management


| URL                                                    | Method | Remarks                        |
|--------------------------------------------------------|--------|--------------------------------|
| `http:localhost:9050/api/users`                         | GET    | Retrieve all users             |
| `http:localhost:9050/api/users/{userId}`                 | GET    | Retrieve user by ID            |
| `http:localhost:9050/api/users`                          | POST   | Add a new user                 |
| `http:localhost:9050/api/users/{userId}`                 | PUT    | Update user by ID              |
| `http:localhost:9050/api/users/{userId}`                 | DELETE | Delete user by ID              |


#### Credential Management
| URL                                                               | Method | Remarks                        |
|--------------------------------------------------------------------|--------|--------------------------------|
| `http:localhost:9050/api/credential`                               | GET    | Retrieves all credentials       |
| `http:localhost:9050/api/credential/{credentialId}`                 | GET    | Retrieves a credential by ID   |
| `http:localhost:9050/api/credential`                                | POST   | Creates a new credential       |
| `http:localhost:9050/api/credential`                                | PUT   | Updates an existing credential  |
| `http:localhost:9050/api/credential/{credentialId}`                 | PUT    | Updates an existing credential by ID |
| `http:localhost:9050/api/credential/{credentialId}`                 | DELETE | Deletes a credential by ID        |
| `http:localhost:9050/api/username/{username}`                      | GET    | Retrieves a credential by username  |


#### Address Management
1. `GET /api/address`: Retrieves all addresses.
2. `GET /api/address/{addressId}`: Retrieves an address by ID.
3. `POST /api/address`: Creates a new address.
4. `PUT /api/address/{addressId}`: Updates an existing address by ID.
5. `DELETE /api/address/{addressId}`: Deletes an address by ID.
#### Verification Token Management
1. `GET /api/verificationTokens`: Retrieves all verification tokens.
2. `GET /api/verificationTokens/{verificationTokenId}`: Retrieves a verification token by ID.
3. `POST /api/verificationTokens`: Creates a new verification token.
4. `PUT /api/verificationTokens`: Updates an existing verification token.
5. `PUT /api/verificationTokens/{verificationTokenId}`: Updates an existing verification token by ID.
6. `DELETE /api/verificationTokens/{verificationTokenId}`: Deletes a verification token by ID.
## Getting Started
To run the microservices locally, follow the instructions provided in each microservice's README file. You'll need to configure the necessary dependencies, databases, and environment variables before running the applications.

## Contributing
We welcome contributions from the community! If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request. For major changes, please discuss them in advance.

