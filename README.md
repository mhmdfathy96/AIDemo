# AIDemo - Spring AI Showcase

This project demonstrates various capabilities of **Spring AI**, showcasing different AI-driven features implemented across multiple branches. Each branch focuses on a specific aspect of AI integration.

## 🚀 Features Overview

Experimental features have been developed across dedicated branches to keep the codebase modular:

### 1. Audio Generation & Transcription (`audio-generation`)
- **Text-to-Speech (TTS)**: Converts input text into high-quality audio bytes using `TextToSpeechModel`.
- **Speech-to-Text (STT)**: Transcribes uploaded audio files into text using `TranscriptionModel` (OpenAI Whisper).
- **Endpoint Example**: `GET /audio/textToSpeech/{text}` and `POST /audio/speechToText`.

### 2. Image Generation (`image-generation`)
- Generates images based on descriptive prompts using the `ImageModel`.
- **Endpoint Example**: `GET /image/generate?message={prompt}`.

### 3. Retrieval Augmented Generation (RAG) (`rag`, `pgvector`, `redisvector`)
- **Context-Aware Chat**: Enhances AI responses by providing relevant context from local documents (`src/main/resources/product_details.txt`).
- **Vector Stores**:
    - **PGVector**: Integration with PostgreSQL for vector similarity search.
    - **Redis**: Integration with Redis for fast vector retrieval.
- **Data Ingestion**: Automatic loading and embedding of documents into the vector store.

### 4. Structured Output Converters (`output-converter`)
- **Bean Output Converter**: Demonstrates how to force the AI to return structured JSON that maps directly to Java POJOs (e.g., a `Movie` object).
- **Endpoint Example**: `GET /movies?genre=action&year=2023&lang=en`.

---

## 🛠️ How to Run

### Prerequisites
- JDK 17 or higher
- Maven
- OpenAI API Key (or other supported providers)
- Docker (for vector store branches like `pgvector` or `redis`)

### Setup Environment
Create a `.env` file or export your API key:
```bash
export OPENAI_API_KEY='your-key-here'
```

### Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🧪 Testing the APIs

You can test the features using `curl` or any API client (Postman/Insomnia).

### Audio Features
```bash
# Text to Speech
curl -o output.mp3 http://localhost:8080/audio/textToSpeech/Hello%20Spring%20AI

# Speech to Text
curl -X POST -F "audio=@path/to/your/audio.mp3" http://localhost:8080/audio/speechToText
```

### Image Generation
```bash
curl "http://localhost:8080/image/generate?message=A%20futuristic%20cityscape%20with%20flying%20cars"
```

### Movie Recommendations (Structured Output)
```bash
curl "http://localhost:8080/movies?genre=sci-fi&year=1999&lang=en"
```

### RAG (Chat with Context)
```bash
curl "http://localhost:8080/chat?message=Tell%20me%20about%20the%20available%20products"
```

---

## 📂 Project Structure
- `src/main/java/com/example/AIDemo`: Contains the controllers and configuration for AI services.
- `src/main/resources`: Contains `application.yaml` and reference documents for RAG.
- `docker-compose.yml`: For spinning up vector databases.
