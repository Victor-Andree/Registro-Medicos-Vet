package webvet.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class CliniVetApplication {

	public static void main(String[] args) {

		SpringApplication.run(CliniVetApplication.class, args);
		System.out.println("API SUSSCEFULL");



		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "admin123"; // Puedes cambiar esta contraseña base
		String encodedPassword = encoder.encode(rawPassword);

		System.out.println("Contraseña original: " + rawPassword);
		System.out.println("Contraseña codificada: " + encodedPassword);


	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		printWelcomeMessage();
		printSwaggerRoutes();

}


	private void printWelcomeMessage() {
		System.out.println("\n" +
				"  ____ _    _ _   _ _______ _____ _   _  _____  \n" +
				" / ___| |  | | \\ | |__   __|_   _| \\ | |/ ____| \n" +
				"| |   | |  | |  \\| |  | |    | | |  \\| | (___   \n" +
				"| |___| |__| | |\\  |  | |    | | | |\\  |\\___ \\  \n" +
				" \\____|_____|_| \\_|  |_|   _|_|_|_| \\_|_____/  \n" +
				"                     | |    | |                  \n" +
				"                     |_|____|_|                  \n");

		System.out.println("✅ Aplicación CliniVet iniciada correctamente");
		System.out.println("🚀 Versión: 1.0.0");
		System.out.println("⏰ Hora de inicio: " + java.time.LocalDateTime.now() + "\n");
	}

	private void printSwaggerRoutes() {
		String port = "8080"; // Puedes obtener esto de las propiedades si es dinámico
		String baseUrl = "http://localhost:" + port;

		System.out.println("📚 Documentación API disponible en:");
		System.out.println("   • Swagger UI: " + baseUrl + "/swagger-ui.html");
	}
}
