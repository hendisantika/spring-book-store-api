# Spring Book Store API

A comprehensive REST API for managing a book store built with Spring Boot, featuring user authentication, book
management, and author relationships.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Authentication](#authentication)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)

## ✨ Features

- **User Management**: User registration, authentication, and profile management
- **Book Management**: CRUD operations for books with detailed information
- **Author Management**: Author profiles and book-author relationships
- **Book Editions**: Multiple editions support with ISBN tracking
- **JWT Authentication**: Secure API access with JSON Web Tokens
- **Data Validation**: Comprehensive input validation
- **Exception Handling**: Global exception handling with proper error responses
- **Database Integration**: MySQL database with Hibernate ORM
- **Docker Support**: Containerized database setup
- **RESTful Design**: Clean REST API architecture

## 🛠 Tech Stack

### Backend

- **Java 21** - Programming language
- **Spring Boot 3.5.5** - Application framework
- **Spring Data JPA** - Data persistence layer
- **Spring Web** - REST API framework
- **Spring Validation** - Input validation
- **Hibernate** - ORM framework

### Database

- **MySQL 9.4** - Primary database
- **HikariCP** - Connection pooling

### Security

- **JWT (JSON Web Tokens)** - Authentication and authorization
- **Spring Security** (via interceptors) - Security framework

### Build & Development

- **Maven** - Dependency management and build tool
- **Docker Compose** - Database containerization
- **Lombok** - Code generation for boilerplate reduction
- **Spring Boot DevTools** - Development productivity tools

### Additional Libraries

- **Jackson** - JSON processing
- **OpenPDF** - PDF generation support
- **Joda Time** - Date/time handling (legacy support)

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **Docker & Docker Compose**
- **Git**

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd spring-book-store-api
   ```

2. **Start the database**
   ```bash
   docker compose up -d
   ```

3. **Build the application**
   ```bash
   mvn clean compile
   ```

## ⚙️ Configuration

### Database Configuration

The application uses MySQL database with the following default settings:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3309/book_store
    username: yu71
    password: 53cret!
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### Docker Compose

The database runs in a Docker container with these specifications:

- **Image**: mysql:9.4.0
- **Port**: 3309 (mapped to container's 3306)
- **Database**: book_store
- **Network**: app_network

### Application Profiles

- **Active Profile**: `dev`
- **JPA Settings**:
    - DDL Auto: `update`
    - Show SQL: `true`
    - Format SQL: `true`

## 🏃‍♂️ Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java

```bash
mvn clean package
java -jar target/spring-book-store-api-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

### Health Check

```bash
curl http://localhost:8080/health
```

## 🔗 API Endpoints

### Authentication Endpoints

| Method | Endpoint  | Description       | Authentication |
|--------|-----------|-------------------|----------------|
| POST   | `/signup` | User registration | No             |
| POST   | `/login`  | User login        | No             |

### Book Management

| Method | Endpoint      | Description     | Authentication |
|--------|---------------|-----------------|----------------|
| GET    | `/books`      | Get all books   | Yes            |
| GET    | `/books/{id}` | Get book by ID  | Yes            |
| POST   | `/books`      | Create new book | Yes            |
| PUT    | `/books/{id}` | Update book     | Yes            |
| DELETE | `/books/{id}` | Delete book     | Yes            |

### User Management

| Method | Endpoint         | Description         | Authentication |
|--------|------------------|---------------------|----------------|
| GET    | `/users/profile` | Get user profile    | Yes            |
| PUT    | `/users/profile` | Update user profile | Yes            |

### Health Check

| Method | Endpoint  | Description               | Authentication |
|--------|-----------|---------------------------|----------------|
| GET    | `/health` | Application health status | No             |
| GET    | `/`       | Root endpoint             | No             |

## 🗄️ Database Schema

### Core Entities

#### User Table

```sql
CREATE TABLE user
(
    id           bigint PRIMARY KEY,
    active       BIT,
    created_at   datetime(6),
    email_id     VARCHAR(255),
    gender       VARCHAR(255),
    login_at     datetime(6),
    login_count  INTEGER,
    name         VARCHAR(255),
    password     VARCHAR(255),
    phone_number VARCHAR(255),
    sso_type     VARCHAR(255),
    updated_at   datetime(6),
    user_type    VARCHAR(255)
);
```

#### Book Table

```sql
CREATE TABLE book
(
    id                  bigint PRIMARY KEY,
    book_type           VARCHAR(255),
    description         VARCHAR(255),
    name                VARCHAR(255),
    year_of_publication INTEGER
);
```

#### Author Table

```sql
CREATE TABLE author
(
    id         bigint PRIMARY KEY,
    created_at varbinary(255),
    gender     VARCHAR(255),
    name       VARCHAR(255),
    updated_at varbinary(255)
);
```

#### Book Edition Table

```sql
CREATE TABLE book_edition
(
    id          bigint PRIMARY KEY,
    description VARCHAR(255),
    isbn        VARCHAR(255),
    page_size   INTEGER,
    price       INTEGER,
    book_id     bigint,
    FOREIGN KEY (book_id) REFERENCES book (id)
);
```

#### Book Author Relationship

```sql
CREATE TABLE book_author
(
    id        bigint PRIMARY KEY,
    author_id bigint NOT NULL,
    book_id   bigint NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);
```

## 🔐 Authentication

The API uses JWT (JSON Web Tokens) for authentication:

1. **Registration/Login**: Use `/signup` or `/login` endpoints to get a JWT token
2. **Token Usage**: Include the token in the `Authorization` header for protected endpoints
3. **Token Format**: `Authorization: Bearer <your-jwt-token>`

### JWT Claims

- `iss`: User ID (issuer)
- `exp`: Expiration time
- `iat`: Issued at time
- `type`: User type
- `name`: User name
- `emailId`: User email

## 🛠 Development

### Project Structure

```
src/main/java/id/my/hendisantika/springbookstoreapi/
├── common/           # Common utilities and exceptions
├── config/           # Spring configuration classes
├── controller/       # REST controllers
├── data/            # Data transfer classes
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── repository/      # Data access layer
├── service/         # Business logic layer
├── util/           # Utility classes
└── validator/      # Input validation classes
```

### Key Components

#### Configuration

- `CustomWebConfig`: Web MVC configuration and interceptors
- `JwtInterceptor`: JWT token validation interceptor
- `RequestMeta`: Request-scoped metadata storage

#### Services

- `BookService`: Book management business logic
- `LoginService`: User authentication and registration

#### Repositories

- `BookRepository`: Book data access
- `UserRepository`: User data access
- `AuthorRepository`: Author data access
- `BookEditionRepository`: Book edition data access

### Adding New Features

1. **Create Entity**: Define JPA entity in `entity/` package
2. **Create Repository**: Extend `JpaRepository` in `repository/` package
3. **Create Service**: Implement business logic in `service/` package
4. **Create Controller**: Add REST endpoints in `controller/` package
5. **Add Validation**: Create validators in `validator/` package

## 🧪 Testing

### Running Tests

```bash
mvn test
```

### Integration Testing

```bash
mvn integration-test
```

### Test Database

Tests use an embedded H2 database for isolation.

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Failed**
    - Ensure Docker container is running: `docker compose ps`
    - Check database credentials in `application.yml`

2. **JWT Authentication Error**
    - Verify token format and expiration
    - Check JWT secret key configuration

3. **Compilation Errors**
    - Ensure Java 21 is installed and configured
    - Run `mvn clean compile` to rebuild

### Logging

Enable debug logging by adding to `application.yml`:

```yaml
logging:
  level:
    id.my.hendisantika.springbookstoreapi: DEBUG
    org.springframework.web: DEBUG
```

## 📝 API Response Format

### Success Response

```json
{
  "status": 200,
  "data": {
    ...
  },
  "error": null
}
```

### Error Response

```json
{
  "status": 400,
  "data": null,
  "error": "Error message"
}
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34

---

## 🚀 Quick Start

```bash
# Clone and setup
git clone <repo-url>
cd spring-book-store-api

# Start database
docker compose up -d

# Run application
mvn spring-boot:run

# Test endpoints
curl http://localhost:8080/health
```

The API will be available at `http://localhost:8080` 🎉
