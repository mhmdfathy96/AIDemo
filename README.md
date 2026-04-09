# AIDemo - Redis Vector Store Migration

This branch (`redisvector`) demonstrates the migration of the vector storage implementation from **pgvector (PostgreSQL)** to **Redis Stack**.

## What was changed?

1.  **Vector Store Migration**: Replaced `pgvector` with `Redis Stack` as the vector database. Redis Stack provides high-performance vector search capabilities through its Search and Query module.
2.  **Infrastructure Update**: Updated `docker-compose.yml` to use the `redis/redis-stack:latest` image, which includes the necessary search and vector modules out of the box.
3.  **Dependency Optimization**:
    *   Removed PostgreSQL and pgvector related dependencies.
    *   Added `spring-ai-starter-vector-store-redis` for seamless integration with Spring AI.
    *   Added `spring-boot-starter-data-redis` for Redis connectivity.
4.  **Configuration**:
    *   Reconfigured `AppConfig.java` to initialize `RedisVectorStore` with `JedisPooled`.
    *   Updated `application.yaml` to include Redis connection settings and vector index configuration (`product-index`).
5.  **Automatic Data Loading**: Added `DataInitializer.java` which automatically reads product information from `src/main/resources/product_details.txt`, splits the text into chunks, and populates the Redis Vector Store on application startup.
6.  **Schema Cleanup**: Removed `schema.sql` which was previously used to initialize PostgreSQL tables and vector extensions.

## Prerequisites

- **Java 21** or higher.
- **Docker** and **Docker Compose**.
- A **Google AI API Key** (for generating embeddings using Google's GenAI model).

## Setup & Running

1.  **Environment Variables**:
    Create a `.env` file in the root directory and add your Google API key:
    ```env
    GOOGLE_API_KEY=your_api_key_here
    ```

2.  **Start Redis Stack**:
    Run the following command to start the Redis container:
    ```bash
    docker-compose up -d
    ```

3.  **Run the Application**:
    Start the Spring Boot application using Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

## Testing

Once the application is running, you can test the vector search functionality using the following methods:

### 1. Swagger UI (Recommended)
Navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to interact with the API directly.

### 2. cURL
You can search for products using a simple cURL command:
```bash
curl -X POST "http://localhost:8080/product?text=laptop"
```

### 3. Verification
- The application will automatically initialize the vector index (`product-index`) on startup.
- Similarity searches are performed using Google's GenAI embeddings, with the results retrieved from Redis.
