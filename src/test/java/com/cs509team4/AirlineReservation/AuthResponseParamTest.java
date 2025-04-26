package com.cs509team4.AirlineReservation;

import com.cs509team4.AirlineReservation.AuthResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthResponseParamTest {

    @Test
    void testParamConstructor() {
        AuthResponse r = new AuthResponse("tok123");
        assertEquals("tok123", r.getToken());
    }
}
