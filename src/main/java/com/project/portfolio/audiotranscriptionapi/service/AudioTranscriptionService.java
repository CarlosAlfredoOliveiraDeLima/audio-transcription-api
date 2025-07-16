package com.project.portfolio.audiotranscriptionapi.service;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AudioTranscriptionService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public AudioTranscriptionService(OpenAiAudioTranscriptionModel transcriptionModel) {
        this.transcriptionModel = transcriptionModel;
    }


    public String transcribeAudio(Resource audioResource){
        try{
            AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(audioResource);

            AudioTranscriptionResponse response = transcriptionModel.call(prompt);

            return response.getResult().getOutput();
        } catch (Exception e){
            throw new RuntimeException("Erro ao transcrever áudio: " + e.getMessage(), e);
        }
    }
}
