package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightDTOTest {
    @Test
    void noArgConstructorAndAccessors() {
        FlightDTO dto = new FlightDTO();

        dto.setId(999);
        dto.setDepartDateTime("2025-05-05T10:00");
        dto.setArriveDateTime("2025-05-05T14:00");

        assertEquals(999, dto.getId());
        assertEquals("2025-05-05T10:00", dto.getDepartDateTime());
        assertEquals("2025-05-05T14:00", dto.getArriveDateTime());
    }
}
