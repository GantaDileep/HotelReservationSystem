# Hotel Reservation System

A RESTful Hotel Reservation System developed using **Java, Spring Boot, Spring Data JPA, and MySQL**. This application provides APIs for managing hotel rooms, customers, reservations, payments, amenities, and user accounts.

## Technologies Used

- Java 25
- Spring Boot 4.1.2
- Spring Data JPA / Hibernate
- MySQL 8
- Maven
- Spring Security
- Spring Boot Actuator
- SpringDoc OpenAPI / Swagger
- AOP and SLF4J Logging
- JUnit 5 and Spring Boot Testing
- Postman

## Project Features

- **Room Management:** Manage hotel rooms and room types.
- **Room Availability:** Check available rooms based on check-in and check-out dates.
- **Customer Management:** Create and manage customer information.
- **Reservation Management:** Create, view, and cancel reservations.
- **Customer Reservation History:** Retrieve reservations associated with a customer.
- **Payment Management:** Manage payment information and payment statuses.
- **Amenity Management:** Manage hotel amenities.
- **User Account Management:** Manage user account information.
- **File Management:** Upload and download files.
- **Exception Handling:** Centralized exception handling with custom exceptions.
- **DTO and Mapper:** Separate request and response objects from entity classes.
- **Auditing:** Track entity creation and modification details.
- **Logging and AOP:** Maintain application logs using Spring AOP.
- **Scheduler:** Execute scheduled reservation-related tasks.
- **API Documentation:** OpenAPI/Swagger configuration.
- **Security:** Spring Security configuration.

## Project Structure

```text
src/main/java/com/demo/hotelreservation/
│
├── aspect/
├── config/
├── controller/
├── dto/
│   ├── request/
│   └── response/
├── entity/
├── enums/
├── exception/
├── mapper/
├── repository/
├── scheduler/
└── service/
    └── impl/

src/main/resources/
├── application.properties
├── application-dev.properties
├── application-prod.properties
└── application-test.properties

src/test/java/
```

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE hotel_reservation_db;
```

Configure the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_reservation_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Set the `DB_PASSWORD` environment variable to your own MySQL password before starting the application.

Do not commit database passwords or other secrets to GitHub.

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/GantaDileep/HotelReservationSystem.git
```

### 2. Open the project

Import the project into Spring Tools for Eclipse as an existing Maven project.

### 3. Configure MySQL

Create the `hotel_reservation_db` database and configure your database credentials.

### 4. Set the environment variable

For Windows PowerShell, for the current terminal session:

```powershell
$env:DB_PASSWORD="your_mysql_password"
```

### 5. Run the application

Run `HotelReservationSystemApplication.java` as a Spring Boot application.

The application runs at:

```text
http://localhost:8080
```

## API Testing

Use Postman to test the REST APIs.

The application provides endpoints for:

- Rooms
- Room Types
- Customers
- Reservations
- Payments
- Amenities
- User Accounts
- File Upload and Download

## API Documentation

If the OpenAPI dependency is configured, access Swagger UI at:

```text
http://localhost:8080/swagger-ui/index.html
```

## GitHub Repository

[HotelReservationSystem](https://github.com/GantaDileep/HotelReservationSystem)

## Author

**Ganta Dileep**

GitHub: [GantaDileep](https://github.com/GantaDileep)
