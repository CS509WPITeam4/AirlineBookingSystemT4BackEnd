package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {

    @Test
    void noArgConstructor_andSettersGetters() {
        BookingDTO dto = new BookingDTO();
        dto.setDepartures(List.of(5L, 6L));
        dto.setReturns(List.of(7L, 8L));

        assertEquals(List.of(5L,6L), dto.getDepartures());
        assertEquals(List.of(7L,8L), dto.getReturns());
    }

    @Test
    void allArgsConstructor_andGetters() {
        BookingDTO dto = new BookingDTO(
                List.of(1L,2L),
                List.of(3L)
        );

        assertEquals(List.of(1L,2L), dto.getDepartures());
        assertEquals(List.of(3L), dto.getReturns());
    }
}
