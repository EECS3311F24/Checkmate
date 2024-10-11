package ca.yorku.checkmate;

import ca.yorku.checkmate.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CheckmateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("http://localhost:8080");
		SpringApplication.run(CheckmateApplication.class, args);;
	}

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) {
		// TODO dirty clear database in case of bad errors
		//repository.deleteAll();
	}
}
