package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightCardDTOTest {
    @Test
    void testGettersSetters() {
        FlightCardDTO dto = new FlightCardDTO();
        dto.setFlightNumber("UA100");
        dto.setDepartAirport("SFO");
        dto.setArriveAirport("LAX");
        dto.setPrice(150.0);
        assertEquals("UA100", dto.getFlightNumber());
        assertEquals("SFO", dto.getDepartAirport());
        assertEquals("LAX", dto.getArriveAirport());
        assertEquals(150.0, dto.getPrice());
    }
}
