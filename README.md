# Product CRUD API

This is a RESTful API for managing products, built with Spring Boot, Spring Data JPA, PostgreSQL, and Spring Validation. The API supports basic CRUD operations, including creating, retrieving, updating, and deleting products.

## Technologies Used

- **Spring Boot**: Framework for building the API.
- **Spring Data JPA**: For ORM and database interactions.
- **PostgreSQL**: Relational database management system.
- **Spring Validation**: To handle validation of input data.

## Endpoints

### 1. Create a Product

- **Endpoint**: `/products`
- **Method**: `POST`
- **Description**: Creates a new product in the database.
- **Request Body**: JSON containing product details.
- **Response**: The created product's details.

**Example Request**:
```json
{
  "name": "Sample Product",
  "price": 19.99,
  "description": "A sample product description."
}
```

### 2. Get All Products

- **Endpoint**: /products
- **Method**: GET
- **Description**: Retrieves a list of all products.
- **Response**: A list of products.

#### Example Response:
```json
[
  {
    "idProduct": "ba446604-6c92-41ed-b988-c6e740af7868",
    "name": "Cadeira congelante",
    "value": 300.00,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/products/ba446604-6c92-41ed-b988-c6e740af7868"
      }
    ]
  },
  {
    "idProduct": "de003492-ea20-44f0-8366-5c1fc0cd0fc9",
    "name": "Maceta",
    "value": 200.00,
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/products/de003492-ea20-44f0-8366-5c1fc0cd0fc9"
      }
    ]
  }
]
```
### 2. Get a Specific Product

- **Endpoint**: /products/{id}
- **Method**: GET
- **Description**: Retrieves the details of a specific product by ID.
- **Response**: The product's details.

#### Example Response:
```json
[
  {
    "idProduct": "ba446604-6c92-41ed-b988-c6e740af7868",
    "name": "Cadeira congelante",
    "value": 300.00,
    "_links": {
      "Products": {
        "href": "http://localhost:8080/products"
      }
    }
  }
]
```
### 3. Update a Product.

- **Endpoint**: /products/{id}
- **Method**: PUT
- **Description**: Updates the details of a specific product by ID.
- **Response**: The updated product's details.

#### Example Response:
```json
[
  {
    "idProduct": "ba446604-6c92-41ed-b988-c6e740af7868",
    "name": "Cadeira congelante UPDATED",
    "value": 500.00,
    "_links": {
      "Products": {
        "href": "http://localhost:8080/products"
      }
    }
  }
]
```

### 5. Delete a Product

- **Endpoint**: /products/{id}
- **Method**: DELETE
- **Description**: Deletes a specific product by ID.
- **Response**: HTTP status 204 No Content.

## Getting Started
### Prerequisites

- **Java 17+**
- **Maven**
- **PostgreSQL**

### Instalation
1. Clone the repository:
```bash
    git clone https://github.com/QuixindoDev/api-java.git
    cd api-java
```
2. Configure the PostgreSQL database in application.properties:

```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
```
3. Run the application:
```bash
    mvn spring-boot:run
```
### Testing

The API can be tested using tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/).

