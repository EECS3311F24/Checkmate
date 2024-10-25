package ca.yorku.checkmate.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A user service that is meant to be used to store
 * and get or edit user information from database
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
        return getUserById(id).isPresent();
    }

    public boolean hasUserByUsername(User user) {
        return getUserByUsername(user.username) != null;
    }

    public boolean addUser(User user) {
        if (hasUserByUsername(user)) return false;
        repository.save(user);
        return true;
    }

    public void updateUser(String id, User newUser) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isEmpty()) return;
        User user = userOptional.get();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
