# 🎬 Movie Booking System – Microservices Architecture

## 📌 Overview

The **Movie Booking System** is a backend application built using **Spring Boot Microservices** that simulates an online movie ticket booking platform (similar to BookMyShow).

The system is designed with **scalability, security, fault tolerance, and clean service boundaries** in mind.  
Each microservice owns its own data and communicates with others using **REST APIs and Feign clients**.

---

## 🎯 Objectives

- Build a real-world **microservices-based backend system**
- Implement **secure user authentication** using JWT
- Enable **movie discovery and seat booking**
- Ensure **seat availability consistency**
- Trigger **notifications after successful bookings**
- Follow **industry-standard backend practices**

---

## 🧩 Microservices Overview

| Service Name               | Description                                                  |
| -------------------------- | ------------------------------------------------------------ |
| **User Service**           | Manages user registration, authentication, and authorization |
| **Movie Service**          | Handles movie catalog and movie metadata                     |
| **Theatre & Seat Service** | Manages theatres, screens, seats, and seat availability      |
| **Booking Service**        | Core orchestration service for booking workflow              |
| **Notification Service**   | Sends booking confirmation notifications                     |
| **API Gateway (Optional)** | Central entry point for routing and security                 |

---

### Key Architectural Principles

- **Database per service**
- **Loose coupling**
- **Service-to-service communication via REST + Feign**
- **JWT-based authentication**
- **Failure isolation**

---

## 🔁 Booking Flow (End-to-End)

1. User logs in via **User Service** and receives a JWT token
2. User selects movie, theatre, and seats
3. Request goes to **Booking Service**
4. Booking Service:
   - Validates JWT
   - Verifies movie via **Movie Service**
   - Checks seat availability via **Theatre & Seat Service**
5. Seats available → booking confirmed
6. Booking stored in Booking DB
7. **Notification Service** sends confirmation
8. Response returned to user

---

## 🗂️ Data Models (High Level)

### User Service

#### User

- **userId**
- **email**
- **password (encrypted)**
- **username**
- **roles**

---

### Movie Service

#### Movie

- **movieId**
- **title**
- **genre**
- **durationMins**

---

### Theatre & Seat Service

#### Theatre

- **theatreId**
- **name**
- **location**

#### Screen

- **screenId**
- **theatreId**
- **screenName**
- **totalSeats**

#### Seat

- **seatId**
- **screenId**
- **seatNumber**
- **seatType** (REGULAR / PREMIUM)
- **isAvailable**

---

### Booking Service

#### Booking

- **bookingId**
- **userId**
- **movieId**
- **theatreId**
- **screenId**
- **seatIds**
- **status** (CREATED / CONFIRMED / CANCELLED)
- **bookingTime**

---

### Notification Service

#### Notification

- **notificationId**
- **userId**
- **bookingId**
- **email**
- **message**
- **status** (SENT / FAILED)
- **createdAt**

## 🔐 Security Design

### Authentication

- **JWT-based authentication** using Spring Security
- Stateless authentication across all microservices
- JWT issued by **User Service** after successful login

### Authorization

- **Role-based access control (RBAC)**
- Supported roles:
  - **USER** – Book tickets, view bookings
  - **ADMIN** – Manage movies, theatres, and seats

### Endpoint Security

- **Secured REST endpoints** for:
  - Booking operations
  - User profile access
- Public endpoints limited to:
  - User registration
  - User login

### Password Security

- **Password encryption** using **BCrypt**
- Plain-text passwords are never stored or transmitted

### Inter-Service Security

- JWT token propagated in **Authorization headers**
- Services validate token before processing requests

### API Gateway (Optional)

- Centralized authentication and authorization
- Request routing to downstream services
- Rate limiting and basic request filtering

## 🛠️ Technology Stack

### Backend

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Security**
- **Spring Data JPA**

### Microservices & Resilience

- **OpenFeign** (Inter-service communication)
- **Resilience4j** (Circuit breaker, retry, fallback)

### Database

- **MySQL / PostgreSQL**

### Testing

- **JUnit 5**
- **Mockito**

### Build Tool

- **Maven**

---

## 🚀 Future Enhancements

### Architecture Improvements

- **API Gateway** using Spring Cloud Gateway
- **Asynchronous communication** using Kafka / RabbitMQ

### Observability

- **Distributed tracing** using Zipkin
- **Centralized logging** using ELK stack

### Functional Enhancements

- **Payment service integration**
- **Seat locking mechanism with TTL**

### DevOps & Deployment

- **Docker** containerization
- **Kubernetes** orchestration

---

## 👨‍💻 Author

### Vikash Katiyar

- Java Backend Developer
- Spring Boot | Microservices | AWS
- **Email:** vikashktr018@gmail.com
