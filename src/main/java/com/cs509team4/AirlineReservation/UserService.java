package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //private int id = 0;

    public User registerUser(UserDTO userDTO) {
        // checks if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // saves new user (id )
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        userRepository.save(user);
        //id++;
        return userRepository.save(user);
    }
}
