package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.User;
import ca.yorku.checkmate.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user information, providing a rest endpoint
 * that allows viewing, adding, editing, or deleting users
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/user")
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

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping(params = "username")
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (!userService.addUser(user)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return ResponseEntity.ok("Deleted user " + user + "!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return ResponseEntity.ok("Deleted all users");
    }
}
