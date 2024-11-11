package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.user.User;
import ca.yorku.checkmate.Model.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint is api/v1/users
 * <br>
 * Controller for users, providing a rest endpoint
 * that allows getting, creating, updating, or deleting users.
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * URL: api/v1/users
     * <br>
     * Gets all users in the database.
     * @return A list of users.
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * URL: api/v1/users/{id}
     * <br>
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
     * URL: api/v1/users?username={username}
     * <br>
     * Gets user by username in the database.
     * @param username The username of the user.
     * @return The user associated with the username if it exists.
     */
    @GetMapping(params = "username")
    public ResponseEntity<List<User>> getUsersByUsername(String username) {
        if (!userService.hasUserByUsername(username)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getUsersByUsername(username));
    }

    /**
     * URL: api/v1/users
     * <br>
     * Create a new user in the database.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<User> createUser(HttpServletResponse response, @RequestBody User user) {
        user = userService.createUser(user);
        if (user == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        Cookie cookie = new Cookie("userId", user.id);
        cookie.setSecure(false);
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * URL: api/v1/users/{id}
     * <br>
     * Update a user by id in the database.
     * @param id The id of the user.
     * @param user The user to be created.
     * @return A response entity with user, informing client
     * with Http status 200 if updated, 401 if not authenticated, 404 if not found.
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return userService.getUserById(id).map(usr -> {
            if (!userService.updateUser(usr, user))
                return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(usr);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/users/authenticate
     * <br>
     * Authenticates user.
     * @param user The user to authenticate.
     * @return A response entity with user, informing client
     * with Http status 200 if authenticated, 401 if not authenticated, 404 if not found.
     */
    @PutMapping("/authenticate")
    public ResponseEntity<User> authenticate(HttpServletResponse response, @CookieValue(name = "userId", required = false) String userId, @RequestBody User user) {
        if (user == null || user.username == null) return ResponseEntity.badRequest().build();
        if (userId != null && !userId.isBlank()) {
            ResponseEntity<User> userEntity = getUserById(userId);
            if (userEntity.getStatusCode().is2xxSuccessful() && userEntity.getBody() != null)
                if (userService.authenticate(userEntity.getBody(), user)) return userEntity;
        }
        final List<User> users = new ArrayList<>();
        users.addAll(userService.getUsersByUsername(user.username));
        users.addAll(userService.getUsersByEmail(user.username));
        if (users.isEmpty()) return ResponseEntity.notFound().build();
        for (final User u : users) {
            if (!userService.authenticate(u, user)) continue;
            Cookie cookie = new Cookie("userId", u.id);
            cookie.setSecure(false);
            cookie.setAttribute("SameSite", "Lax");
            response.addCookie(cookie);
            return ResponseEntity.ok(u);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * URL: api/v1/users/{id}?oldPassword={oldPassword}&password={password}
     * <br>
     * Set a user password.
     * @param id The id of the user.
     * @param oldPassword The old hashed password
     * @param password The new hashed password
     * @return A response entity with user, informing client
     * with Http status 200 if updated, 409 if not updated, 404 if not found.
     */
    @PatchMapping("{id}")
    public ResponseEntity<User> setPassword(@PathVariable("id") String id, @RequestParam(name = "oldPassword", required = false) String oldPassword, @RequestParam(name = "password") String password) {
        if (password == null || password.isEmpty()) return ResponseEntity.badRequest().build();
        return userService.getUserById(id).map(user -> {
            if (userService.setUserPassword(user, oldPassword, password))
                return ResponseEntity.ok(user);
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/users/{id}
     * <br>
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
     * URL: api/v1/users
     * <br>
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