package ca.yorku.checkmate.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A user service that is meant to be used to store
 * and get user information from database
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User getUserByID(String ID) {
        return repository.findByID(ID);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(String ID, User newUser) {
        User user = getUserByID(ID);
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }
}
