package ca.yorku.checkmate.Model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A User service that will get, create, update, and delete User
 * data that is stored in the UserRepository. It is to be used by a controller
 * that will get and receive information to be performed on the Mongodb database.
 */
@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> getUserById(String id) {
        return repository.findById(id);
    }

    public boolean hasUserById(String id) {
        return repository.existsById(id);
    }

    public boolean hasUserByUsername(String username) {
        return getUserByUsername(username) != null;
    }

    public boolean createUser(User user) {
        if (hasUserByUsername(user.username)) return false;
        repository.save(user);
        return true;
    }

    public void updateUser(String id, User newUser) {
        getUserById(id).ifPresent(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            repository.save(user);
        });
    }

    public void patchUserUsername(User user, String username) {
        user.setUsername(username);
        repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
