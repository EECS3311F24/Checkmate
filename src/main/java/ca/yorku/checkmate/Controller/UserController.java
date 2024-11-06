package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.user.User;
import ca.yorku.checkmate.Model.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Endpoint is api/v1/users</p>
 * Controller for users, providing a rest endpoint
 * that allows getting, creating, updating, or deleting users.
 */
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <p>URL: api/v1/users</p>
     * Gets all users in the database.
     * @return A list of users.
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * <p>URL: api/v1/users/{id}<p>
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
     * <p>URL: api/v1/users?username={username}</p>
     * Gets user by username in the database.
     * @param username The username of the user.
     * @return The user associated with the username if it exists.
     */
    // TODO this should return a list of users
    @GetMapping(params = "username")
    public ResponseEntity<User> getUserByUsername(String username) {
        if (!userService.hasUserByUsername(username)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    /**
     * <p>URL: api/v1/users</p>
     * Create a new user in the database.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<User> createUser(HttpServletResponse response, @RequestBody User user) {
        if (!userService.createUser(user)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        Cookie cookie = new Cookie("userId", user.id);
        cookie.setSecure(false);
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * <p>URL: api/v1/users/{id}</p>
     * Update a user by id in the database.
     * @param id The id of the user.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 200 if updated, 404 if not found.
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return userService.getUserById(id).map(usr -> {
            userService.updateUser(id, user);
            return ResponseEntity.ok(usr);
        }).orElse(ResponseEntity.notFound().build());
    }

    //TODO example of Patch, Patch is like update but less info sent
    @PatchMapping("{id}")
    public ResponseEntity<User> patchUsername(@PathVariable("id") String id, @RequestBody String username) {
        if (username == null || username.isEmpty()) return ResponseEntity.badRequest().build();
        return userService.getUserById(id).map(user -> {
            userService.patchUserUsername(user, username);
            return ResponseEntity.ok(user);
        }).orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /**
     * <p>URL: api/v1/users/{id}</p>
     * Delete a user by id in the database.
     * @param id The id of the user.
     * @return A response entity with user, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        return userService.getUserById(id).map(user -> {
            userService.deleteUser(user);
            return ResponseEntity.ok(user);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>URL: api/v1/users</p>
     * Delete all users in the database.
     * @return A response entity with a message, informing client
     * with Http status 200.
     */
    @Deprecated
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        userService.deleteAll();
        return ResponseEntity.ok("Deleted all users");
    }
}
