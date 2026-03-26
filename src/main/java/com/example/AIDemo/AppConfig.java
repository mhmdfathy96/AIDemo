package com.example.AIDemo;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPooled;

@Configuration
public class AppConfig {
    @Bean
    VectorStore vectorStore(JedisPooled jedisPooled, EmbeddingModel embeddingModel) {
        return RedisVectorStore.builder(jedisPooled, embeddingModel).indexName("product-index")
                .initializeSchema(true)
                .build();
    }

    @Bean
    JedisPooled jedisPooled() {
        return new JedisPooled("localhost", 6379);
    }
}
