package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightDTOTest {

    @Test
    void testNoArgConstructorAndGettersSetters() {
        FlightDTO dto = new FlightDTO();

        dto.setId(123);
        dto.setDepartDateTime("2025-05-03T10:00");
        dto.setArriveDateTime("2025-05-03T14:00");

        assertEquals(123, dto.getId());
        assertEquals("2025-05-03T10:00", dto.getDepartDateTime());
        assertEquals("2025-05-03T14:00", dto.getArriveDateTime());
    }

    @Test
    void testDefaultValues() {
        FlightDTO dto = new FlightDTO();
        // before setting, primitive id → 0, object strings → null
        assertEquals(0, dto.getId());
        assertNull(dto.getDepartDateTime());
        assertNull(dto.getArriveDateTime());
    }
}
