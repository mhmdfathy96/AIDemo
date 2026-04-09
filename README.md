# AI Demo - pgvector Integration

This branch (`pgvector`) demonstrates the integration of **Spring AI** with **pgvector** to perform semantic similarity searches on product data.

## What we did here

1.  **Infrastructure Setup**:
    *   Configured a **PostgreSQL** database with the `pgvector` extension using `docker-compose.yml`.
    *   Integrated **Spring AI** with the `spring-ai-starter-vector-store-pgvector` dependency.

2.  **Data Ingestion**:
    *   Created `DataInitializer.java` which runs on startup.
    *   It reads product descriptions from `src/main/resources/product_details.txt`.
    *   Uses `TokenTextSplitter` to chunk the text into manageable pieces.
    *   Generates embeddings using **Google GenAI** and stores them in the `pgvector` vector store.

3.  **Search API**:
    *   Implemented `MainController.java` with a `POST /product` endpoint.
    *   The endpoint accepts a `text` parameter and returns the most relevant documents from the vector store based on cosine similarity.

## Prerequisites

*   **Java 21** or higher.
*   **Docker** and **Docker Compose**.
*   **Google AI API Key**: You need a valid API key for Google Gemini/Embeddings.

## Setup and Run

1.  **Start the Database**:
    Ensure Docker is running and execute:
    ```bash
    docker-compose up -d
    ```

2.  **Configure Environment**:
    The project includes a `.env` file template. Open `.env` and provide your Google AI API Key:
    ```env
    GOOGLE_API_KEY=your_actual_key_here
    ```
    *(Note: The `OPENAI_API_KEY` is present but currently unused as the project is configured for Google GenAI).*

3.  **Build and Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## How to Test

Once the application is running, it will automatically ingest data from `product_details.txt`. You can then test the similarity search:

### 1. Swagger UI
The easiest way to test is via Swagger UI:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 2. cURL
Test with a natural language query:
```bash
curl -X POST "http://localhost:8080/product?text=I need something for my desk to help with cables"
```

### Search Examples:
*   "noise canceling earbuds" -> Matches Bluetooth Earbuds
*   "kitchen storage" -> Matches Glass Food Storage Containers
*   "comfortable sleep" -> Matches Memory Foam Pillow

