package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthRequestTest {
    @Test
    public void testAuthRequestFields() {
        AuthRequest req = new AuthRequest();
        req.setIdentifier("testuser");
        req.setPassword("password");

        assertEquals("testuser", req.getIdentifier());
        assertEquals("password", req.getPassword());
    }
}