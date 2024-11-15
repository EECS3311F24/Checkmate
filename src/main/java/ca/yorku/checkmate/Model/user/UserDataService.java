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

    public UserData updateUserData(UserData userData, UserData placeholder) {
        // TODO make sure nothing in placeholder is null or bad data.
        if (userData == null || placeholder == null) return null;
        userData.setTheme(placeholder.theme);
        userData.setLanguage(placeholder.language);
        userData.setWins(placeholder.wins);
        userData.setLoses(placeholder.loses);
        userData.setGamesPlayed(placeholder.gamesPlayed);
        repository.save(userData);
        return userData;
    }

    public boolean saveUserData(UserData userData) {
        repository.save(userData);
        return true;
    }

    public void deleteUserData(String id) {
        getUserData(id).ifPresent(repository::delete);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}