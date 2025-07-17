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


	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		printWelcomeMessage();
		printSwaggerRoutes();

}


	private void printWelcomeMessage() {
		System.out.println("\n" +
				" __        ______  _____  ____   _    _ _______ _______ \n" +
				" \\ \\      / / __ \\|  __ \\|  _ \\ | |  | |_   _|__   __|\n" +
				"  \\ \\ /\\ / / |  | | |__) | |_) || |  | | | |    | |   \n" +
				"   \\ V  V /| |  | |  _  /|  _ < | |  | | | |    | |   \n" +
				"    | | | | |__| | | \\ \\| |_) || |__| |_| |_   | |   \n" +
				"    |_| |_|\\____/|_|  \\_\\____/  \\____/|_____|  |_|   \n");


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
