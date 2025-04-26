package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    void testGettersAndSetters() {
        Booking b = new Booking();
        b.setId(7L);
        b.setUserId(20L);
        b.setFlightNumber("WN200");
        b.setDepartureAirport("BOS");
        b.setArrivalAirport("MIA");
        b.setDepartureDateTime(LocalDateTime.of(2025,2,1,8,30));
        b.setArrivalDateTime(LocalDateTime.of(2025,2,1,11,30));
        b.setStatus("PENDING");

        assertEquals(7L, b.getId());
        assertEquals("WN200", b.getFlightNumber());
        assertEquals("PENDING", b.getStatus());

        // toString covers string representation
        assertTrue(b.toString().contains("WN200"));
    }
}
