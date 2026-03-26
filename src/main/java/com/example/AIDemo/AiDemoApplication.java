package com.example.AIDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AiDemoApplication {

	public static void main(String[] args) {
		// Only load .env file for local development
		// In prod/stg (Docker/K8s/cloud), env vars are provided by the platform
		String activeProfile = System.getProperty("spring.profiles.active",
				System.getenv("SPRING_PROFILES_ACTIVE"));

		// Load .env only for dev profile or when profile is not explicitly set (local
		// dev)
		if (activeProfile == null || activeProfile.isEmpty() || activeProfile.contains("dev")) {
			Dotenv dotenv = Dotenv.configure()
					.ignoreIfMissing()
					.load();

			dotenv.entries().forEach(entry -> {
				System.setProperty(entry.getKey(), entry.getValue());
			});
		}
		SpringApplication.run(AiDemoApplication.class, args);
	}

}
