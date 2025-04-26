package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDTOTest {

    @Test
    void testFromAndToBooking() {
        Booking original = new Booking();
        original.setId(5L);
        original.setUserId(10L);
        original.setFlightNumber("DL100");
        original.setDepartureAirport("JFK");
        original.setArrivalAirport("LAX");
        original.setDepartureDateTime(LocalDateTime.of(2025,1,1,10,0));
        original.setArrivalDateTime(LocalDateTime.of(2025,1,1,14,0));
        original.setStatus("CONFIRMED");

        BookingDTO dto = BookingDTO.fromBooking(original);
        assertEquals(5L, dto.getId());
        assertEquals("DL100", dto.getFlightNumber());
        assertEquals("CONFIRMED", dto.getStatus());

        Booking back = dto.toBooking();
        assertEquals(original.getUserId(), back.getUserId());
        assertEquals(original.getArrivalAirport(), back.getArrivalAirport());
    }
}
