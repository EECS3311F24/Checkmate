package ca.yorku.checkmate.Model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final UserDataRepository repository;

    @Autowired
    public UserDataService(UserDataRepository repository) {
        this.repository = repository;
    }

    public boolean saveUserData(UserData userData) {
        repository.save(userData);
        return true;
    }
}