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

    public User getUserByID(String ID) {
        return repository.findByID(ID);
    }

    public boolean addUser(User user) {
        if (getUserByUsername(user.username) != null)
            return false;
        repository.save(user);
        return true;
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
