# AI Demo Project

A Spring Boot application demonstrating AI capabilities using **Spring AI** and **OpenAI**. Specifically, this project implements Audio Generation (Text-to-Speech) and Transcription (Speech-to-Text) features.

## 🚀 Features

- **Text-to-Speech (TTS)**: Convert string text into high-quality audio bytes.
- **Speech-to-Text (Transcription)**: Transcribe uploaded audio files into text using Whisper.
- **Swagger Documentation**: Interactive API documentation for easy testing.

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 4.x** (Early version)
- **Spring AI**: Integration with OpenAI.
- **SpringDoc OpenAPI**: For API documentation and testing UI.
- **Maven**: Project management and build tool.

## 🚦 Getting Started

### Prerequisites

- Java 21 or higher installed.
- Maven (or use the provided `./mvnw` wrapper).
- An **OpenAI API Key**.

### Configuration

1. Locate the `.env` file in the root directory.
2. Add your OpenAI API key:
   ```env
   OPENAI_API_KEY=your_actual_api_key_here
   ```

### Running the Application

Use the Maven wrapper to start the server:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## 🧪 Testing the APIs

The easiest way to test the APIs is through the **Swagger UI**.

1. Start the application.
2. Open your browser and navigate to: `http://localhost:8080/swagger-ui/index.html`
3. You will see two main endpoints:

### 1. Text to Speech
- **Endpoint**: `GET /audio/textToSpeech/{text}`
- **Description**: Provide a text string as a path variable, and the API will return a byte array (audio file).
- **Test**: Click "Try it out", enter some text, and "Execute". You can download the response to listen to the audio.

### 2. Speech to Text
- **Endpoint**: `POST /audio/speechToText`
- **Description**: Upload an audio file (e.g., `.mp3`, `.wav`) as `multipart/form-data`.
- **Test**: Click "Try it out", upload a small audio file in the `audio` field, and "Execute". The transcribed text will appear in the response body.

## 📁 Project Structure

- `AudioGenContoller.java`: The primary REST controller handling the audio logic.
- `application.yaml`: Configuration for Spring AI and OpenAI integration.
- `.env`: (Ignored/Template) Where sensitive keys are stored locally.
