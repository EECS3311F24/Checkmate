package ca.yorku.checkmate;

import ca.yorku.checkmate.Model.user.User;
import ca.yorku.checkmate.Model.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckmateApplicationTests {

	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
		repository.deleteAll();

		// save a couple of users in database
		repository.save(new User("Alice", "Alice@yorku.ca"));
		repository.save(new User("Bob", "Bob@yorku.ca"));
		repository.save(new User("Karen", "karen1956@yahoo.com"));
		repository.save(new User("Jake", "jake2013@gmail.com"));
	}
}
