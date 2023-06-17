# Parking Service Project
This is a Java project for a parking service. It includes controllers that handle various operations related to user authentication, booking, managing cars, and searching for parking places.
___
## Controller Functions

### AuthController

- `GET /auth/signup`: Displays the signup form.
- `POST /auth/customer/sign-up`: Handles the signup request for a customer.
- `POST /auth/manager/sign-up`: Handles the signup request for a manager.
- `GET /auth/login`: Displays the login form.
- `POST /auth/log-in`: Handles the login request.
- `GET /auth/change-password`: Displays the change password form.
- `PATCH /auth/change-password`: Handles the change password request.

### BookingController

- `POST /bookings/{place-id}`: Books a place based on the provided place ID.
- `DELETE /bookings/{booking-id}/cars/{car-id}`: Deletes a booking based on the provided booking and car IDs.

### CarController

- `GET /cars/new`: Displays the form to add a new car.
- `POST /cars`: Handles the request to create a new car.
- `DELETE /cars/{id}`: Deletes a car based on the provided ID.
- `GET /cars/{id}`: Retrieves a car based on the provided ID.

### PlacesController

- `GET /places`: Displays the search form for places.
- `POST /places`: Retrieves places based on the search criteria.

### UserController

- `GET /users/profile`: Displays the user's profile information.
___
## Dependencies
The project relies on the following dependencies:

- Spring Framework Dependencies:
    - spring-core
    - spring-web
    - spring-context
    - spring-webmvc
    - spring-jdbc
    - spring-data-jpa

- PostgreSQL Database Dependency:
    - postgresql

- Lombok Dependency:
    - lombok

- Log4j Dependencies:
    - log4j-core
    - log4j-api

- Jakarta Servlet API Dependency:
    - jakarta.servlet-api

- Hibernate Validator Dependency:
    - hibernate-validator

- Thymeleaf Dependency:
    - thymeleaf-spring6

- Hibernate Core Dependency:
    - hibernate-core

- jBCrypt Dependency:
    - jbcrypt
___
