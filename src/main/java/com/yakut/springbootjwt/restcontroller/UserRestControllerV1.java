package com.yakut.springbootjwt.restcontroller;

import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import com.yakut.springbootjwt.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {

    private final UserService userService;

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/new/add")
    public User saveUser(@RequestBody User user) throws UserNotSavedToDataBaseException {
        return userService.saveUser(user);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
