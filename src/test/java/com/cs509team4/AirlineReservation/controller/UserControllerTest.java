package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.AuthRequest;
import com.cs509team4.AirlineReservation.AuthResponse;
import com.cs509team4.AirlineReservation.User;
import com.cs509team4.AirlineReservation.UserController;
import com.cs509team4.AirlineReservation.UserDTO;
import com.cs509team4.AirlineReservation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("user");
        dto.setEmail("email@example.com");
        dto.setPassword("pwd");

        when(userService.registerUser(dto))
                .thenReturn(new User("user", "email@example.com", "pwd"));

        ResponseEntity<String> response = userController.signUp(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully!", response.getBody());
        verify(userService).registerUser(dto);
    }

    @Test
    void testSignUp_Failure() {
        UserDTO dto = new UserDTO();
        dto.setUsername("user");
        dto.setEmail("email@example.com");
        dto.setPassword("pwd");

        when(userService.registerUser(dto))
                .thenThrow(new RuntimeException("Email already in use!"));

        ResponseEntity<String> response = userController.signUp(dto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Email already in use!", response.getBody());
    }

    @Test
    void testLogin_Success() {
        AuthRequest req = new AuthRequest();
        req.setIdentifier("user");
        req.setPassword("pwd");
        User user = new User("user", "email@example.com", "pwd");

        when(userService.authenticate("user", "pwd"))
                .thenReturn("token123");
        when(userService.getUserDetails("user"))
                .thenReturn(Optional.of(user));

        ResponseEntity<AuthResponse> response = userController.login(req);

        assertEquals(200, response.getStatusCodeValue());
        AuthResponse body = response.getBody();
        assertNotNull(body);
        assertEquals("token123", body.getToken());
        assertEquals(user, body.getUser());
    }

    @Test
    void testLogin_Failure() {
        AuthRequest req = new AuthRequest();
        req.setIdentifier("user");
        req.setPassword("wrong");

        when(userService.authenticate("user", "wrong"))
                .thenThrow(new RuntimeException("Invalid password"));

        ResponseEntity<AuthResponse> response = userController.login(req);

        assertEquals(401, response.getStatusCodeValue());
        AuthResponse body = response.getBody();
        assertNotNull(body);
        assertNull(body.getToken());
        assertNull(body.getUser());
    }
}
