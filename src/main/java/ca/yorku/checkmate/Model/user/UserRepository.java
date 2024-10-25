package ca.yorku.checkmate.Model.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database for Users
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
