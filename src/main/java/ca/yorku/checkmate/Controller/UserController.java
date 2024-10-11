package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.User;
import ca.yorku.checkmate.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user information, providing a rest endpoint
 * that allows viewing, adding, editing, or deleting users
 */
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/api/v1/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping(path = "/api/v1/user", params = "username")
    public User getUser(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/search")
    public String showSearchUserForm(User user) {
        return "search-user";
    }

    @PostMapping("/search-user")
    public String searchUser(@Validated User u, BindingResult result, Model model) {
        User user = userService.getUserByUsername(u.username);
        if (result.hasErrors()) {
            return "search-user";
        }
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        if (!userService.addUser(user)) return "Username taken!";
        model.addAttribute("username", user.getUsername());
        return "user";
    }

    @GetMapping("/edit/{ID}")
    public String showUpdateForm(@PathVariable("ID") String ID, Model model) {
        User user = userService.getUserByID(ID);

        if (user == null) return "Error! No user by " + ID;

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{ID}")
    public String updateUser(@PathVariable("ID") String ID, @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-user";
        }

        userService.updateUser(ID, user);
        return "redirect:/";
    }

    @GetMapping("/delete/{ID}")
    public String deleteUser(@PathVariable("ID") String ID) {
        User user = userService.getUserByID(ID);
        userService.deleteUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete-all")
    public String deleteAll() {
        return "delete-all";
    }

    @PostMapping("/clear-database")
    public String clearDatabase() {
        userService.deleteAll();
        return "redirect:/";
    }
}
