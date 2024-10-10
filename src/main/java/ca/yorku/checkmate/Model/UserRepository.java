package ca.yorku.checkmate.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByID(String ID);
}
