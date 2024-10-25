package ca.yorku.checkmate.Model.user;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

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

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', email='%s', createdOn='%s']",
                id, username, email, createdOn
        );
    }

}
