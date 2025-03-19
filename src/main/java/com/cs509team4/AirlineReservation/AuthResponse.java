package com.cs509team4.AirlineReservation;

public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() { return token; }
    public String getMessage() { return message; }
}
