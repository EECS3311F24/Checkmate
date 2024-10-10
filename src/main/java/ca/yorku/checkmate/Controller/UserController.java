package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.User;
import ca.yorku.checkmate.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for user information, providing a rest endpoint
 * that allows looking at all users, or a specific user
 */
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(params = "username")
    public User getUsers(String username) {
        return userService.getUser(username);
    }
}
