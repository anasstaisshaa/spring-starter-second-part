# Spring Starter: Part Two

This project is a continuation of the **Spring Starter** series, showcasing advanced Spring Boot concepts and practices. It includes the implementation of additional features, such as better configuration, security, and testing techniques.

## Features

- **Spring Boot Configuration**: Demonstrates how to configure application properties and profiles.
- **RESTful API**: Implements a simple REST API with CRUD operations.
- **Spring Security**: Integrates Spring Security for authentication and authorization.
- **JPA and Hibernate**: Uses JPA and Hibernate for data persistence.
- **Unit Testing**: Includes unit tests with JUnit and Mockito for ensuring code quality.

## Getting Started

### Prerequisites

To run this project, you'll need to have the following installed:

- Java 17 or higher
- Maven 3.8+
- PostgreSQL (or any preferred database)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/anasstaisshaa/spring-starter-second-part.git
    ```

2. Navigate to the project directory:

    ```bash
    cd spring-starter-second-part
    ```

3. Configure your database settings in the `application.properties` or `application.yml` file.

4. Build and run the application using Maven:

    ```bash
    mvn spring-boot:run
    ```

### Running Tests

To run the unit tests, execute:

    ```bash
    mvn test
    ```

## API Endpoints

The application provides the following RESTful endpoints:

- `GET /api/v1/resource`: Fetch all resources.
- `GET /api/v1/resource/{id}`: Fetch a single resource by ID.
- `POST /api/v1/resource`: Create a new resource.
- `PUT /api/v1/resource/{id}`: Update an existing resource.
- `DELETE /api/v1/resource/{id}`: Delete a resource by ID.

## Technologies Used

- **Spring Boot**: Backend framework.
- **Spring Data JPA**: For database interactions.
- **Spring Security**: For authentication and authorization.
- **PostgreSQL**: Database management system.
- **JUnit & Mockito**: Testing frameworks.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
