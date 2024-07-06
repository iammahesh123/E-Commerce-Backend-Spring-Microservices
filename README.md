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
- `Spring Security`: For securing the application with JWT.
- `JavaMailSender`: For sending emails.

## Microservices Overview

Our eCommerce platform consists of the following microservices:
1. `User Service`: Handles user registration, authentication, and profile management.
2. `Product Service`: Manages product catalog, inventory, and product information.
3. `Order Service`: Processes and manages orders placed by customers.
4. `Payment Service`: Handles payment processing and integration with payment gateways.

## Entity-Relationship Diagram (ERD)
![ERD](https://github.com/iammahesh123/E-Commerce-Backend-Spring-Microservices/blob/master/EntityFinal.jpg)

### Features

- `User registration`: Allows users to register and create new accounts.
- `User authentication`: Supports user authentication using credentials.
- `Profile management`: Enables users to update their profile information.
- `User information retrieval`: Provides endpoints for retrieving user details.
- `Account activation`: Allows users to activate their accounts via email.
- `Password reset`: Allows users to reset their passwords via email.
- `Forgot password`: Allows users to request a password reset link.

### Endpoints

#### User Management

| URL                                     | Method | Remarks                       |
|-----------------------------------------|--------|-------------------------------|
| `http://localhost:9050/api/users`       | GET    | Retrieve all users            |
| `http://localhost:9050/api/users/{userId}` | GET    | Retrieve user by ID           |
| `http://localhost:9050/api/users`       | POST   | Add a new user                |
| `http://localhost:9050/api/users/{userId}` | PUT    | Update user by ID             |
| `http://localhost:9050/api/users/{userId}` | DELETE | Delete user by ID             |

#### Credential Management

| URL                                           | Method | Remarks                              |
|-----------------------------------------------|--------|--------------------------------------|
| `http://localhost:9050/api/credentials`       | GET    | Retrieves all credentials            |
| `http://localhost:9050/api/credentials/{credentialId}` | GET    | Retrieves a credential by ID         |
| `http://localhost:9050/api/credentials`       | POST   | Creates a new credential             |
| `http://localhost:9050/api/credentials`       | PUT    | Updates an existing credential       |
| `http://localhost:9050/api/credentials/{credentialId}` | PUT    | Updates an existing credential by ID |
| `http://localhost:9050/api/credentials/{credentialId}` | DELETE | Deletes a credential by ID           |
| `http://localhost:9050/api/username/{username}` | GET    | Retrieves a credential by username   |

#### Address Management

| URL                                      | Method | Remarks                       |
|------------------------------------------|--------|-------------------------------|
| `http://localhost:9050/api/addresses`    | GET    | Retrieves all addresses       |
| `http://localhost:9050/api/addresses/{addressId}` | GET    | Retrieves an address by ID    |
| `http://localhost:9050/api/addresses`    | POST   | Creates a new address         |
| `http://localhost:9050/api/addresses/{addressId}` | PUT    | Updates an existing address by ID |
| `http://localhost:9050/api/addresses/{addressId}` | DELETE | Deletes an address by ID      |

#### Verification Token Management

| URL                                                 | Method | Remarks                                |
|-----------------------------------------------------|--------|----------------------------------------|
| `http://localhost:9050/api/verificationTokens`      | GET    | Retrieves all verification tokens      |
| `http://localhost:9050/api/verificationTokens/{verificationTokenId}` | GET    | Retrieves a verification token by ID   |
| `http://localhost:9050/api/verificationTokens`      | POST   | Creates a new verification token       |
| `http://localhost:9050/api/verificationTokens`      | PUT    | Updates an existing verification token |
| `http://localhost:9050/api/verificationTokens/{verificationTokenId}` | DELETE | Deletes a verification token by ID     |

#### Account Activation and Password Management

| URL                                           | Method | Remarks                              |
|-----------------------------------------------|--------|--------------------------------------|
| `http://localhost:9050/api/users/activate-account` | POST   | Sends account activation email       |
| `http://localhost:9050/api/users/forgot-password`  | POST   | Sends password reset email           |
| `http://localhost:9050/api/users/reset-password`   | POST   | Resets user password                 |

### Getting Started

To run the microservices locally, follow the instructions provided in each microservice's README file. You'll need to configure the necessary dependencies, databases, and environment variables before running the applications.

### Contributing

We welcome contributions from the community! If you find any bugs or have suggestions for improvements, please open an issue or submit a pull request. For major changes, please discuss them in advance.
