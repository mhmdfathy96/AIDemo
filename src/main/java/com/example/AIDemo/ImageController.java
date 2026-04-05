package com.example.AIDemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ImageController {

    private ImageModel imageModel;
    private ChatClient chatClient;

    public ImageController(ImageModel imageModel, ChatModel chatModel) {
        this.imageModel = imageModel;
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("image/{description}")
    public String generateImage(@PathVariable String description) {
        String prompt = """
                    Generate Image With Description : {description}
                """;
        ImageOptions options = ImageOptionsBuilder.builder()
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);

        return imageModel.call(imagePrompt).getResult().getOutput().getUrl();
    }

    @PostMapping(value = "image/describe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String describeImage(@RequestParam String query, @RequestParam MultipartFile file) {
        return chatClient.prompt()
                .user(us -> us.text(query).media(MimeType.valueOf(file.getContentType()), file.getResource())).call()
                .content();
    }

}
