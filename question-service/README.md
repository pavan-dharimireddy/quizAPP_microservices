# Question Service

This is a microservice built with Spring Boot that handles the management of quiz questions. It's designed to be part of a larger quiz application ecosystem and provides a robust API for retrieving, adding, and generating questions.

## Technology Stack

*   **Java Version:** 25
*   **Spring Boot Version:** 4.0.6
*   **Spring Cloud Version:** 2025.1.1
*   **Database:** PostgreSQL
*   **Dependencies:**
    *   Spring Boot Starter Web (WebMVC)
    *   Spring Boot Starter Data JPA
    *   PostgreSQL Driver
    *   Lombok
*   *(Note: Eureka Client and OpenFeign dependencies are currently commented out but present in the `pom.xml` for future integration into a service mesh).*

## Project Structure

The project follows a standard Spring Boot architecture:

*   **`controller/`**: Contains the REST controllers exposing the API endpoints.
*   **`service/`**: Contains the business logic for managing questions.
*   **`model/`**: Contains the entity classes (e.g., `Question`, `QuestionWrapper`, `Response`) and database mappings.
*   **`repo/`**: Contains the JPA repository interfaces for database interactions.

## API Documentation

The `QuestionController` exposes the following REST API endpoints under the base path `/question`:

### 1. Get All Questions
*   **Endpoint:** `GET /question/allQuestions`
*   **Description:** Retrieves a list of all questions available in the database.
*   **Returns:** `ResponseEntity` containing a list of `Question` objects.

### 2. Get Questions by Category
*   **Endpoint:** `GET /question/category/{value}`
*   **Description:** Retrieves a list of questions belonging to a specific category.
*   **Path Variable:**
    *   `value`: The category name (e.g., "Java", "Python").
*   **Returns:** A list of `Question` objects.

### 3. Add a New Question
*   **Endpoint:** `POST /question/addQuestion`
*   **Description:** Adds a new question to the database.
*   **Request Body:** A JSON representation of the `Question` object.
*   **Returns:** `ResponseEntity<String>` indicating the success or failure of the operation.

### 4. Generate Questions for a Quiz
*   **Endpoint:** `GET /question/generate`
*   **Description:** Retrieves a specified number of question IDs for a given category to generate a quiz. This is typically called by a separate Quiz Service.
*   **Request Parameters:**
    *   `categoryName`: The category of the quiz.
    *   `numQ`: The number of questions to generate.
*   **Returns:** `ResponseEntity<List<Integer>>` containing a list of question IDs.

### 5. Get Questions by IDs
*   **Endpoint:** `POST /question/getQuestions`
*   **Description:** Retrieves the details of multiple questions based on a list of question IDs. This endpoint returns `QuestionWrapper` objects, which typically exclude sensitive information like the correct answer, suitable for sending to the client taking the quiz.
*   **Request Body:** A JSON array of integers (`List<Integer>`) representing the question IDs.
*   **Returns:** `ResponseEntity<List<QuestionWrapper>>` containing the requested questions.

### 6. Calculate Score
*   **Endpoint:** `POST /question/getScore`
*   **Description:** Calculates the total score based on the user's responses to a set of questions.
*   **Request Body:** A JSON array of `Response` objects containing the question IDs and the selected answers.
*   **Returns:** `ResponseEntity<Integer>` containing the calculated score.

## Setup and Running

1.  **Database Configuration:** Ensure you have a PostgreSQL instance running. You will need to configure the database connection properties in your `application.properties` or `application.yml` file (which is currently missing in `src/main/resources` but will be needed). Typical properties include:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```
2.  **Build the project:**
    ```bash
    mvn clean install
    ```
3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## Future Enhancements
*   Integrate with Spring Cloud Eureka for service discovery.
*   Implement API documentation using Swagger/OpenAPI.
*   Add more comprehensive unit and integration tests.