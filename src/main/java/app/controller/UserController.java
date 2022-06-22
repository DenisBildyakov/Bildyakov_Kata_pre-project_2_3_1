package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/")
    public String openStartPage(ModelMap model) {
        List<User> users = userService.findAllUsers();

        // Добавил просто чтобы не пустая таблица была
        if (users.isEmpty()) {
            userService.addUser(new User("Maha", "Smirnova", 33));
            userService.addUser(new User("Misha", "Krokodilov", 24));
            userService.addUser(new User("Dima", "Borisov", 18));
            userService.addUser(new User("Vasya", "Pupkin", 16));
            userService.addUser(new User("Kostya", "Gradov", 52));
        }

        model.addAttribute("users", users);
        return "/index";
    }

    @GetMapping("addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/addUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findUserById(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String updateUpdate(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
