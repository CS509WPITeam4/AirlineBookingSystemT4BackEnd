package com.cs509team4.AirlineReservation;

public class AuthResponse {
    private String token;
    private User user;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public User getUser() { return user; }
}
