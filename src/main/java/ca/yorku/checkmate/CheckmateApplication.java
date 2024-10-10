package ca.yorku.checkmate;

import ca.yorku.checkmate.Model.User;
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
		SpringApplication.run(CheckmateApplication.class, args);
	}

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Temp database creations
		// TODO make simple frontend to input post requests to add new users from view.
		// Remove all old users
		repository.deleteAll();

		// save a couple of users in database
		repository.save(new User("Alice", "Alice@yorku.ca"));
		repository.save(new User("Bob", "Bob@yorku.ca"));
		repository.save(new User("Karen", "karen1956@yahoo.com"));
		repository.save(new User("Jake", "jake2013@gmail.com"));

		// fetch all users
		System.out.println("Users list:");
		System.out.println("-------------------------------");
		repository.findAll().forEach(System.out::println);
		System.out.println();
	}
}
