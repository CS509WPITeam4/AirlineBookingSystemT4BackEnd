package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private JwtUtil jwtUtil;
    @InjectMocks private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("testuser"); dto.setEmail("email"); dto.setPassword("pwd");
        when(userRepository.existsByEmail("email")).thenReturn(false);
        User saved = new User("testuser","email","pwd");
        when(userRepository.save(any())).thenReturn(saved);

        User result = userService.registerUser(dto);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void testRegisterUser_EmailExists() {
        UserDTO dto = new UserDTO(); dto.setEmail("email");
        when(userRepository.existsByEmail("email")).thenReturn(true);
        assertThrows(RuntimeException.class, () -> userService.registerUser(dto));
    }

    @Test
    void testAuthenticate_Success() {
        User user = new User("u","e","pwd");
        when(userRepository.findByUsernameOrEmail("u")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(user)).thenReturn("token");

        String token = userService.authenticate("u","pwd");
        assertEquals("token", token);
    }

    @Test
    void testAuthenticate_UserNotFound() {
        when(userRepository.findByUsernameOrEmail("u")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.authenticate("u","pwd"));
    }

    @Test
    void testAuthenticate_InvalidPassword() {
        User user = new User("u","e","pwd");
        when(userRepository.findByUsernameOrEmail("u")).thenReturn(Optional.of(user));
        assertThrows(RuntimeException.class, () -> userService.authenticate("u","wrong"));
    }

    @Test
    void testGetUserDetails() {
        User user = new User("u","e","pwd");
        when(userRepository.findByUsernameOrEmail("u")).thenReturn(Optional.of(user));

        Optional<User> opt = userService.getUserDetails("u");
        assertTrue(opt.isPresent());
    }
}
