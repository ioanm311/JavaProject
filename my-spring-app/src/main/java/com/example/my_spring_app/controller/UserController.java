package com.example.my_spring_app.controller;

import com.example.my_spring_app.model.User;
import com.example.my_spring_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Operations related to user authentication and management")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user", description = "Registers a new user in the system.")
    public User registerUser(@RequestBody @Parameter(description = "User object to be registered") User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Authenticates a user using email and password.")
    public User loginUser(
            @RequestParam @Parameter(description = "Email of the user") String email,
            @RequestParam @Parameter(description = "Password of the user") String password) {
        User user = userService.loginUser(email, password);
        if (user == null) {
            throw new RuntimeException("Email or password is incorrect");
        }
        return user;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all users", description = "Fetches all users from the system (admin only).")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a user", description = "Deletes a user from the system (admin only).")
    public void deleteUser(@PathVariable @Parameter(description = "ID of the user to be deleted") Long id) {
        try {
            userService.deleteUser(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("User not found", e);
        }
    }
}
