package com.example.my_spring_app.service;

import com.example.my_spring_app.model.User;
import com.example.my_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // verificare email
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("The email is already in use.");
        }
        // salvam user
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    // Obține lista tuturor utilizatorilor (admin only)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Șterge un utilizator după ID (admin only)
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}