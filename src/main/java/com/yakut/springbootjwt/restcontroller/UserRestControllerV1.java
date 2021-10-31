package com.yakut.springbootjwt.restcontroller;

import com.yakut.springbootjwt.exception.NoUserUnderThisId;
import com.yakut.springbootjwt.exception.UserNotSavedToDataBaseException;
import com.yakut.springbootjwt.models.User;
import com.yakut.springbootjwt.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('users:read')")
    public User getUserById(@PathVariable Long id) throws NoUserUnderThisId {
        return userService.findUserById(id);
    }

    @PostMapping("/new/add")
    @PreAuthorize("hasAuthority('users:write')")
    public User saveUser(@RequestBody User user) throws UserNotSavedToDataBaseException {
        return userService.saveUser(user);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('users:write')")
    public User updateUser(@RequestBody User user) throws UserNotSavedToDataBaseException {
        return userService.saveUser(user);
    }
}
