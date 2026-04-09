# Product Intelligence RAG Demo

This repository demonstrates a **Retrieval-Augmented Generation (RAG)** implementation using **Spring AI**, **Google Gemini**, and **Redis Vector Store**. It allows you to query a product catalog using natural language, where the AI retrieves relevant product details to provide accurate and context-aware answers.

---

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.3.0+
- **AI Integration**: Spring AI (Google Gemini)
- **Vector Database**: Redis (with Search and JSON modules)
- **Data Initialization**: Automatic document embedding on startup

---

## 🚀 Getting Started

### 1. Prerequisites
- **JDK 21**
- **Docker** (for Redis)
- **Google Gemini API Key** (Get it from [Google AI Studio](https://aistudio.google.com/))

### 2. Environment Setup
Create a `.env` file in the root directory and add your Google API key:
```env
GOOGLE_API_KEY=your_gemini_api_key_here
```

### 3. Start Infrastructure
Run Redis with the required modules using Docker Compose:
```bash
docker-compose up -d
```

### 4. Run the Application
```bash
./mvnw spring-boot:run
```

---

## 🧪 How to Test

The application automatically loads product data from `src/main/resources/product_details.txt` into the Redis Vector Store upon startup.

### Query the API
You can use `curl` to ask questions about the products:

```bash
curl -X POST "http://localhost:8080/product?text=Which+running+shoes+provide+good+arch+support?"
```

### Interactive Documentation (Swagger)
Explore the API and test endpoints directly through the Swagger UI:
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📂 Key Components

- **`MainController.java`**: Exposes the `/product` endpoint using `ChatClient` and `QuestionAnswerAdvisor`.
- **`DataInitializer.java`**: Handles document splitting and ingestion into Redis.
- **`AppConfig.java`**: Manages the configuration for `RedisVectorStore` and `JedisPooled`.
- **`product_details.txt`**: The source knowledge base for the RAG system.
