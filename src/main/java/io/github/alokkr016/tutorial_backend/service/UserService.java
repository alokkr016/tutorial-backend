package io.github.alokkr016.tutorial_backend.service;

import io.github.alokkr016.tutorial_backend.dto.UserRequest;
import io.github.alokkr016.tutorial_backend.model.User;
import io.github.alokkr016.tutorial_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequest userRequest) {
        // Check if user already exists
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); // Encode the password
        userRepository.save(user); // Save the user to the database
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
