package webvet.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CliniVetApplication {

	public static void main(String[] args) {

		SpringApplication.run(CliniVetApplication.class, args);
		System.out.println("API SUSSCEFULL");
	}

}
