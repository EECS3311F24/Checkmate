package ca.yorku.checkmate.Model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {
    private final UserDataRepository repository;

    @Autowired
    public UserDataService(UserDataRepository repository) {
        this.repository = repository;
    }

    public Optional<UserData> getUserData(String id) {
        return repository.findById(id);
    }

    public boolean hasUserDataById(String id) {
        return repository.existsById(id);
    }

    public void createUserData(String id) {
        if (hasUserDataById(id)) return;
        UserData userData = new UserData().setId(id);
        repository.save(userData);
    }

    public void deleteUserData(String id) {
        getUserData(id).ifPresent(repository::delete);
    }

    public boolean saveUserData(UserData userData) {
        repository.save(userData);
        return true;
    }
}