package com.example.AIDemo;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private VectorStore vectorStore;

    public MainController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostMapping("/product")
    public List<Document> getProducts(@RequestParam String text) {
        return vectorStore.similaritySearch(text);
    }
}
