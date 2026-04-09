# AI Demo: Output Converter Branch

This branch demonstrates how to use **Spring AI** and its **Output Converter** (specifically `BeanOutputConverter`) to map AI model responses to structured Java objects (POJOs).

## 🚀 Features

- **Structured Movie Recommendations**: Recommends a movie based on genre, year, and language, returning a structured `Movie` object.
- **Bulk Recommendations**: Recommends a list of movies (e.g., top 10) and maps them to a `List<Movie>`.
- **Automatic JSON Mapping**: Uses `BeanOutputConverter` to ensure the LLM's raw JSON response is correctly parsed into Java classes.
- **Swagger Documentation**: Interactive UI to test the endpoints easily.

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 4.0.4**
- **Spring AI (2.0.0-M3)**: For OpenAI integration and output conversion.
- **SpringDoc OpenAPI (3.0.2)**: For Swagger UI.
- **Maven**: Build and dependency management.

## 🚦 Getting Started

### Prerequisites

- Java 21 or higher.
- OpenAI API Key.

### Configuration

1. Create or update the `.env` file in the project root:
   ```env
   OPENAI_API_KEY=your_openai_api_key_here
   ```
2. The application uses `application.yaml` for Spring AI settings.

### Running the Application

Start the server using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`.

## 🧪 Testing the APIs

Navigate to the **Swagger UI** to interact with the endpoints:

**URL**: `http://localhost:8080/swagger-ui/index.html`

### 1. Raw String Recommendation (Baseline)
- **Endpoint**: `GET /main/recommendMovie`
- **Params**: `genre`, `year`, `lang`
- **Output**: Raw JSON string (no conversion).
- **Controller**: `MainController.java`

### 2. Single Movie Recommendation (Structured)
- **Endpoint**: `GET /movie/recommend`
- **Params**: `genre`, `year`, `lang`
- **Output**: A single `Movie` JSON object.
- **Controller**: `MovieController.java`

### 3. List of Movie Recommendations (Structured)
- **Endpoint**: `GET /movie/recommend/list`
- **Params**: `genre`, `year`, `lang`
- **Output**: A JSON array of `Movie` objects.
- **Controller**: `MovieController.java`

## 📁 Key Files in this Branch

- `Movie.java`: The POJO representing the movie data structure.
- `MovieController.java`: Contains the logic for using `ChatClient` and `BeanOutputConverter`.
- `pom.xml`: Includes the necessary Spring AI starters.

---
*Note: This README is specific to the `output-converter` branch.*
