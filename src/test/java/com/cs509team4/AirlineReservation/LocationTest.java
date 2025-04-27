package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void testGettersSetters() {

        Location loc = new Location(
                "JFK",
                "New York",
                "USA",
                "John F Kennedy International"
        );
        loc.setId(1L);

        assertEquals(1L, loc.getId());
        assertEquals("JFK", loc.getIataCode());
        assertEquals("New York", loc.getCityName());
        assertEquals("USA", loc.getCountry());
        assertEquals("John F Kennedy International", loc.getAirportName());
    }
}
