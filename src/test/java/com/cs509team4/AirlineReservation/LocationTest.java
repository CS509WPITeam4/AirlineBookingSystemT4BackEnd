package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void testLocationGettersSetters() {
        Location location = new Location();

        // Only test simple fields, avoid database behavior
        location.setCityName("New York");
        location.setCountry("USA");
        location.setAirportName("JFK Airport");

        // Don't touch id if it's auto-generated
        assertEquals("New York", location.getCityName());
        assertEquals("USA", location.getCountry());
        assertEquals("JFK Airport", location.getAirportName());
    }
}
