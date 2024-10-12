package ca.yorku.checkmate.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A user service that is meant to be used to store
 * and get or edit user information from database
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

    public User getUserById(String id) {
        return repository.findByid(id);
    }

    public boolean addUser(User user) {
        if (getUserByUsername(user.username) != null)
            return false;
        repository.save(user);
        return true;
    }

    public void updateUser(String id, User newUser) {
        User user = getUserById(id);
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
