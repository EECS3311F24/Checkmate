package ca.yorku.checkmate.Model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.Date;

/**
 * User model that represents a simple user
 */
public class User {
    @Id
    public String id;
    @CreatedDate
    public Date createdOn;
    public String username;
    public String email;
    private String passwordHash;

    public User() {}

    public User(String username, String email) {
        new User(username, email);
    }

    @PersistenceCreator
    public User(String username, String email, @JsonProperty("password") String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    protected String getPasswordHash() {
        return passwordHash;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', email='%s', createdOn='%s']",
                id, username, email, createdOn
        );
    }

}
