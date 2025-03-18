package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

    public String authenticateUser(UserDTO userDTO) {
        String identifier = (userDTO.getUsername() != null && !userDTO.getUsername().isEmpty())
                ? userDTO.getUsername()
                : userDTO.getEmail();

        User user = userRepository.findByUsernameOrEmail(identifier);

        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return "mock-jwt-token";
        }
        throw new RuntimeException("Invalid username/email or password.");
    }
}
