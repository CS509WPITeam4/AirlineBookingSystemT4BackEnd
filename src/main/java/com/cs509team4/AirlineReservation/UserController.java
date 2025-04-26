package com.cs509team4.AirlineReservation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:5173") // Adjust to your frontend

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Register a new user",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(value = "{\"username\":\"alice\",\"email\":\"alice@example.com\",\"password\":\"pass123\"}")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Email already in use or validation error")
            }
    )

    // Register new users
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Authenticate user and obtain JWT token",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthRequest.class),
                            examples = @ExampleObject(value = "{\"identifier\":\"alice\",\"password\":\"pass123\"}")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "JWT token and user details returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class),
                                    examples = @ExampleObject(value = "{\"token\":\"eyJhbGci…\",\"user\":{\"id\":1,\"username\":\"alice\",\"email\":\"alice@example.com\"}}")
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials")
            }
    )

    // User login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        System.out.println("Received login request: identifier=" + request.getIdentifier() + ", password=" + request.getPassword());
        try {
            String token = userService.authenticate(request.getIdentifier(), request.getPassword());
            User user = userService.getUserDetails(request.getIdentifier()).get();
            return ResponseEntity.ok(new AuthResponse(token, user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, null));
        }
    }

    @Operation(
            summary = "Get user details by username or email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return userService.getUserDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
