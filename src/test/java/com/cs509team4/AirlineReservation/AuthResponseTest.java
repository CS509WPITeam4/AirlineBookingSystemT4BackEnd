package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthResponseTest {

    @Test
    public void testTokenConstructorGetter() {
        AuthResponse response = new AuthResponse("abc123token");
        assertEquals("abc123token", response.getToken());
    }
}