package com.example.AIDemo;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private ChatClient chatClient;

    public MovieController(ChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/movie/recommend")
    public Movie getMovie(@RequestParam String genre, @RequestParam String year, @RequestParam String lang) {
        String temp = """
                You are a movie recommendation system.
                Recommend a movie based on the following criteria:
                Genre: {genre}
                Year: {year}
                Language: {lang}

                Return the response in JSON format with the following fields:
                title: string
                year: int
                language: string
                rating: float
                description: string
                """;

        PromptTemplate promptTemplate = new PromptTemplate(temp);

        Prompt prompt = promptTemplate.create(Map.of("genre", genre, "year", year, "lang", lang));
        return chatClient.prompt(prompt).call()
                .entity(new BeanOutputConverter<Movie>(Movie.class));
    }

    @GetMapping("/movie/recommend/list")
    public List<Movie> getMovies(@RequestParam String genre, @RequestParam String year, @RequestParam String lang) {
        String temp = """
                You are a movie recommendation system.
                Recommend a list of movies (10 movies) based on the following criteria:
                Genre: {genre}
                Year: {year}
                Language: {lang}

                Return the response in JSON format with the following fields:
                title: string
                year: int
                language: string
                rating: float
                description: string
                """;

        PromptTemplate promptTemplate = new PromptTemplate(temp);

        Prompt prompt = promptTemplate.create(Map.of("genre", genre, "year", year, "lang", lang));
        return chatClient.prompt(prompt).call()
                .entity(new BeanOutputConverter<List<Movie>>(new ParameterizedTypeReference<List<Movie>>() {
                }));
    }
}
