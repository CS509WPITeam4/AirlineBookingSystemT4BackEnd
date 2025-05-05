package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
    @Test
    void testGettersAndSetters() {

        Flight f = new Flight(){};
        f.setFlightId(42);
        f.setFlightNumber("FL42");
        f.setDepartAirport("ABC");
        f.setArriveAirport("XYZ");
        f.setDepartDateTime("2025-07-01T09:00");
        f.setArriveDateTime("2025-07-01T12:00");

        assertEquals(42, f.getId());
        assertEquals("FL42", f.getFlightNumber());
        assertEquals("ABC", f.getDepartAirport());
        assertEquals("XYZ", f.getArriveAirport());
        assertEquals("2025-07-01T09:00", f.getDepartDateTime());
        assertEquals("2025-07-01T12:00", f.getArriveDateTime());
    }
}
