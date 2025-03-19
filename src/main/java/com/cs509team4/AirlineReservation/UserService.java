package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(UserDTO userDTO) {
        // checks if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // saves new user (id )
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        userRepository.save(user);
        return userRepository.save(user);
    }

    // User Authentication (Login)
    public String authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Check password validity
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token upon successful login
        return jwtUtil.generateToken(user);
    }

}