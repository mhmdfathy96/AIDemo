package com.example.AIDemo;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private VectorStore vectorStore;
    private ChatClient chatClient;

    public MainController(VectorStore vectorStore, ChatModel chatModel) {
        this.vectorStore = vectorStore;
        this.chatClient = ChatClient.create(chatModel);
    }

    @PostMapping("/product")
    public String getProducts(@RequestParam String text) {
        return chatClient.prompt(text)
                .advisors(List.of(QuestionAnswerAdvisor.builder(vectorStore).build()))
                .call()
                .content();
    }
}
