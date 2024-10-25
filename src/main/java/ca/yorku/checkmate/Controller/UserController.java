package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.User;
import ca.yorku.checkmate.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Endpoint is api/v1/user</p>
 * Controller for users, providing a rest endpoint
 * that allows getting, creating, updating, or deleting users.
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

    /**
     * <p>URL: api/v1/user</p>
     * Gets all users in the database.
     * @return A list of users.
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * <p>URL: api/v1/user/{id}<p>
     * Gets user by id in the database.
     * @param id The id of the user.
     * @return The user associated with the id if it exists.
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * <p>URL: api/v1/user?username={username}</p>
     * Gets user by username in the database.
     * @param username The username of the user.
     * @return The user associated with the username if it exists.
     */
    @GetMapping(params = "username")
    public User getUserByUsername(String username) {
        // TODO user Response Entity?
        return userService.getUserByUsername(username);
    }

    /**
     * <p>URL: api/v1/user</p>
     * Create a new user in the database.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (!userService.addUser(user)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * <p>URL: api/v1/user/{id}</p>
     * Update a user by id in the database.
     * @param id The id of the user.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 200 if updated, 201 if created, 409 if not created.
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if (!userService.hasUserById(id)) return createUser(user);
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    /**
     * <p>URL: api/v1/user/{id}</p>
     * Delete a user by id in the database.
     * @param id The id of the user.
     * @return A response entity with a message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        return userService.getUserById(id).map(user -> {
            userService.deleteUser(user);
            return ResponseEntity.ok("Deleted user " + user + "!");
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>URL: api/v1/user</p>
     * Delete all users in the database.
     * @return A response entity with a message, informing client
     * with Http status 200.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return ResponseEntity.ok("Deleted all users");
    }
}
