package com.example.AIDemo;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.audio.tts.TextToSpeechOptions;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AudioGenContoller {
    private TextToSpeechModel ttsModel;
    private TranscriptionModel transcriptionModel;

    public AudioGenContoller(TextToSpeechModel ttsModel, TranscriptionModel transcriptionModel) {
        this.ttsModel = ttsModel;
        this.transcriptionModel = transcriptionModel;
    }

    @GetMapping("audio/textToSpeech/{text}")
    public byte[] generateAudio(@PathVariable String text) {
        TextToSpeechOptions options = TextToSpeechOptions.builder()
                .build();
        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text, options);
        return ttsModel.call(prompt).getResult().getOutput();
    }

    @PostMapping(value = "audio/speechToText", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postMethodName(@RequestBody MultipartFile audio) {
        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .build();
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(
                audio.getResource(), options);
        return transcriptionModel.call(prompt).getResult().getOutput();
    }

}
