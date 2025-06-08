package webvet.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

}
