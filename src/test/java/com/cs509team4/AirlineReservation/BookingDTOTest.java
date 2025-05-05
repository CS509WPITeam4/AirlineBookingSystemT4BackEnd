package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {
    @Test
    void allArgsConstructorAndGetters() {
        BookingDTO dto = new BookingDTO(List.of(1L,2L), List.of(3L));
        assertEquals(List.of(1L,2L), dto.getDepartures());
        assertEquals(List.of(3L),   dto.getReturns());
    }

    @Test
    void noArgCtorAndSetters() {
        BookingDTO dto = new BookingDTO();
        dto.setDepartures(List.of(10L));
        dto.setReturns   (List.of(20L,30L));

        assertEquals(List.of(10L), dto.getDepartures());
        assertEquals(List.of(20L,30L), dto.getReturns());
    }
}
