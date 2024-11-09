package ca.yorku.checkmate.Model.user;

import org.apache.commons.codec.digest.DigestUtils;
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

    public List<User> getUsersByUsername(String username) {
        return repository.findAll().stream()
                .filter(user -> user.username.equals(username))
                .toList();
    }

    public Optional<User> getUserById(String id) {
        return repository.findById(id);
    }

    public boolean authenticatePassword(User user, String password) {
        // TODO password is hashed of (hashed text + id)
        return user.getPasswordHash().equals(hashPassword(password));
    }

    public boolean hasUserById(String id) {
        return repository.existsById(id);
    }

    public boolean hasUserByUsername(String username) {
        return !getUsersByUsername(username).isEmpty();
    }

    public boolean createUser(User user) {
        repository.save(user);
        return repository.findAll().stream()
                .filter(u -> u.samePassword(user))
                .findFirst()
                .map(u -> {
                    u.setPasswordHash(hashPasswordWithId(user.id, user.getPasswordHash()));
                    repository.save(u);
                    return true;
                })
                .orElse(false);
    }

    public boolean updateUser(User user, User placeholder) {
        placeholder.setPasswordHash(hashPasswordWithId(user.id, placeholder.getPasswordHash()));
        if (!user.samePassword(placeholder))
            return false;
        user.setUsername(placeholder.getUsername());
        user.setEmail(placeholder.getEmail());
        repository.save(user);
        return true;
    }

    public boolean setUserPassword(User user, String oldPassword, String password) {
        if (user.getPasswordHash() != null) {
            if (!user.getPasswordHash().equals(hashPasswordWithId(user.id, oldPassword)))
                return false;
        }
        user.setPasswordHash(hashPasswordWithId(user.id, password));
        repository.save(user);
        return true;
    }

    private String hashPassword(String password) {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }

    private String hashPasswordWithId(String id, String password) {
        return new DigestUtils("SHA3-256").digestAsHex(password + id);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
