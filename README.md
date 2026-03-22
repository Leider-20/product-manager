# Product API

This project is a REST API created with Spring Boot for product management. Allows you to create, search, compare and delete products.

The API follows a layered architecture and uses DTOs and mappers to separate internal models from API responses.

---

# API Design

The application follows a layered architecture composed of several components, each with a clear responsibility.

## Controller Layer

The Controller layer handles HTTP requests and responses. It exposes the endpoints that clients use to interact with the API.

## Service Layer

The Service layer contains the business logic of the application. It processes requests coming from the controller.

## Repository Layer

The repository layer handles data storage and retrieval. This project uses a JSON file to simulate data persistence.

## Model

Represents the entities of the system and their data structure. Defines the attributes of each entity and is used to transport and store information within the application.

## DTOs

DTOs (Data Transfer Objects) are used to separate the internal domain model from the objects exposed by the API. This ensures that internal changes to the model do not affect the external API contract.

## Mapper

The MapStruct dependency is used to automatically map between entities and DTOs.

---

# API Endpoints

## Create Product

**POST /products**

Creates a new product. The client sends the product information in the request body and the API stores it and returns the created product.

### Example Request

```
{
  "id": 5,
  "name": "Honor T15",
  "imageUrl": "https://example.com/HonorT15.jpg",
  "description": "Android smartphone",
  "price": 1000.0,
  "rating": 4.7,
  "specifications": {
    "storage": "128GB",
    "ram": "8GB"
  }
}
```

---

## Get All Products

**GET /products**

Returns a list of all stored products.

---

## Get Product by ID

**GET /products/{id}**

Returns a product that matches the given identifier.

If the product does not exist, the API returns a **404 Not Found** response.

---

## Compare Products

**GET /products/compare?ids=1&ids=2**

**GET /products/compare?ids=1,2**


Returns the list of products corresponding to the provided IDs so they can be compared.

---

## Delete Product

**DELETE /products/{id}**

Deletes a product using its identifier.

If the product does not exist, the API returns a **404 Not Found** response.

---

## Delete All Products

**DELETE /products**

Deletes all stored products.

---

# Setup Instructions

## Requirements

* Java 21
* Maven

## Run the Project

Clone the repository:

```
git clone <repository-url>
```

Navigate to the project directory:

```
cd <project-folder>
```

Run the application:

```
mvn spring-boot:run
```

Compile the project

```
mvn install
```
```
mvn compile
```

Delete previous build files
```
mvn clean
```

Compile the project and delete the previous build files

```
mvn clean install
```
```
mvn clean compile
```

Compile the project without running the tests and delete previous build files

```
mvn clean install -DskipTests
```
```
mvn clean compile -DskipTests
```

Build the project

```
mvn package
```

The API will start at:

```
http://localhost:8080
```

---

# Running Tests

To run the unit tests execute:

```
mvn test
```

The tests verify the behavior of the service, repository and controller layers.

* JUnit is used as the testing framework.
* Mockito is used to mock dependencies and isolate the units being tested.

---

# Key Architectural Decisions

## DTO Layer

DTOs were introduced to separate the internal data model from the data exposed by the API. This improves flexibility and protects the internal structure of the application.

## MapStruct

MapStruct is used to automatically generate mappings between entities and DTOs. This significantly reduces manual mapping code and improves readability.

## Lombok

Lombok is used to reduce repetitive code by automatically generating constructors, getters, setters and constructor patterns.

## Layered Architecture

The separation between controller, service, and repository improves maintainability, testability, and scalability of the application.

## Exception Handling

Basic error handling is implemented using a global exception handler (`@RestControllerAdvice`).  

Custom exceptions such as `NoSuchResourceFoundException` are used to return meaningful HTTP responses (e.g., 404 when a product is not found).

A generic exception handler is also included to handle unexpected server errors.
## Unit Testing

Unit tests were implemented for the service, repository and controller layers.

---

# Possible Improvements

Possible future improvements include:

* Adding a real database for persistence
* Implementing API documentation with Swagger
* Improving validation and error reporting
