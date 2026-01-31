# EateryThem Backend

**EateryThem** is a platform designed to help you manage your meals and recipes effortlessly. Plan your meals in cycles so you never have to worry about what to eat tomorrow. Create recipes, group them into cycles, and generate meal plans using your own cycles or those shared by others. Organize your day’s meals—breakfast, lunch, and dinner—and even involve your family by letting them vote to skip or replace meals. Ultimately, the final plan is always yours.  

This repository contains the **backend** of EateryThem, built with **Java Spring Boot**.

---

## Features

- **Recipe Management:** Create, update, and delete recipes.  
- **Meal Cycles:** Group recipes into reusable cycles for easy planning.  
- **Meal Planning:** Generate daily meal plans using your cycles or shared cycles.  
- **Family Interaction:** Invite family members and allow them to vote on meals.  
- **User Authentication:** Secure access with JWT-based authentication.  
- **RESTful API:** Provides endpoints for frontend integration or third-party clients.  

---

## Technologies Used

### Core Stack

- **Java 17** – The primary programming language.  
- **Spring Boot 3.5.5** – Framework for building RESTful APIs efficiently.  
- **Spring Data JPA** – Simplifies database access and management using repositories.  
- **PostgreSQL** – Relational database for storing users, recipes, cycles, and meal plans.  
- **Spring Security + JWT** – Secures API endpoints and handles authentication.  
- **MapStruct** – Automatically maps between Entities and DTOs for clean data transfer.  
- **Spring Validation** – Ensures incoming data meets required constraints.  

### Other Libraries

- **Lombok** – Reduces boilerplate code (getters, setters, constructors).  
- **Google Drive API** – Integration for recipe image uploading.  

---

## Architecture

The backend follows a standard **Repository-Service-Controller** pattern:

1. **Controller:** Handles HTTP requests and responses.  
2. **Service:** Contains business logic and interacts with repositories.  
3. **Repository:** Interfaces with the database using Spring Data JPA.  

This separation ensures **clean code**, **testability**, and **maintainability**.  

**Entity ↔ DTO Mapping:** MapStruct is used to convert between database entities and DTOs automatically, keeping layers decoupled.  

**Security:** JWT tokens handle stateless authentication, ensuring secure access for each user.  

---

## Learning Outcomes

Building EateryThem helped me gain hands-on experience with:

- Designing and implementing **RESTful APIs** with Spring Boot.  
- Securing endpoints using **Spring Security** and **JWT**.  
- Mapping objects efficiently using **MapStruct**.  
- Database design and query optimization in **PostgreSQL**.  
- Structuring projects using **Repository-Service-Controller** architecture.  
- Implementing **validation and exception handling** for robust APIs.  
- Integrating **external APIs** (Google Drive) in a Spring Boot project.  

---

## Getting Started

### Prerequisites

- Java 17 or higher  
- Maven  
- PostgreSQL  

### Setup

1. Clone the repository:  
   ```bash
   git clone https://github.com/your-username/eaterythem.git
2. Configure `application.properties` with your database and JWT settings:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/eaterythem
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

   # JWT configuration
   jwt.secret=your_jwt_secret
   jwt.expiration=3600000

3. Create a `.env` file in the project root (optional but recommended for local development and Docker).

   Add the following variables to the `.env` file (example):

   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/eaterythem
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   SPRING_JPA_HIBERNATE_DDL_AUTO=update

   # JWT configuration
   JWT_SECRET=your_jwt_secret
   JWT_EXPIRATION=3600000
   ```

   Note: `.env` variables can be used by Docker Compose or local run configurations to override values in `application.properties`.
3. Place the credentials.json file for Google Drive image uploading in credentials folder at root.