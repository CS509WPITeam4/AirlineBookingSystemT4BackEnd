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
    public String authenticate(String identifier, String password) {
        System.out.println("0");
        //Optional<User> userOptional = userRepository.findByEmail(email);
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(identifier);

        System.out.println("1");

        if (userOptional.isEmpty()) {
            System.out.println("Empty");
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        System.out.println("2");

        // Check password validity
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        System.out.println("3");


        System.out.println("USER ID " + user.getId());

        // Generate JWT token upon successful login
        String token = jwtUtil.generateToken(user);
        System.out.println("4");
        return token;
    }

    public Optional<User> getUserDetails(String identifier) {
        return userRepository.findByUsernameOrEmail(identifier);
    }

}

