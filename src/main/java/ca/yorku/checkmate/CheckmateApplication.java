package ca.yorku.checkmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CheckmateApplication {

	public static void main(String[] args) {

		SpringApplication.run(CheckmateApplication.class, args);
	}
}