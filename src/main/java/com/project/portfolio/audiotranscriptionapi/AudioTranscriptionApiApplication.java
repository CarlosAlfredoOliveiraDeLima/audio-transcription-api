package com.project.portfolio.audiotranscriptionapi;

import com.project.portfolio.audiotranscriptionapi.service.AudioTranscriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AudioTranscriptionApiApplication {

	private final AudioTranscriptionService transcriptionService;

	public AudioTranscriptionApiApplication(AudioTranscriptionService transcriptionService) {
		this.transcriptionService = transcriptionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AudioTranscriptionApiApplication.class, args);
	}

	@PostConstruct
	public void runTranscription() {
		System.out.println("=== Iniciando transcrição de áudio ===");

		try {
			// Carregar o arquivo de áudio da raiz do projeto
			Resource audioResource = new FileSystemResource("audio.webm");

			// Verificar se o arquivo existe
			if (!audioResource.exists()) {
				System.err.println("❌ Arquivo audio.webm não encontrado na raiz do projeto");
				return;
			}

			System.out.println("📁 Arquivo encontrado: " + audioResource.getFilename());
			System.out.println("🔄 Enviando para OpenAI...");

			// Fazer a transcrição
			String transcription = transcriptionService.transcribeAudio(audioResource);

			// Mostrar o resultado
			System.out.println("\n=== RESULTADO DA TRANSCRIÇÃO ===");
			System.out.println(transcription);
			System.out.println("================================");

		} catch (Exception e) {
			System.err.println("❌ Erro durante a transcrição: " + e.getMessage());
			e.printStackTrace();
		}
	}
}