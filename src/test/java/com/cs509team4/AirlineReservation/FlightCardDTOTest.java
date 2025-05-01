package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightCardDTOTest {

    @Test
    void testAllArgsConstructorAndGetter() {
        // prepare
        Flight f = new Flight();
        List<Flight> list = List.of(f);

        // call
        FlightCardDTO dto = new FlightCardDTO(list);

        // verify
        assertNotNull(dto.getFlights());
        assertEquals(1, dto.getFlights().size());
        assertSame(f, dto.getFlights().get(0));
    }

    @Test
    void testNoArgConstructorAndSetters() {
        // prepare
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        List<Flight> list = List.of(f1, f2);

        // call
        FlightCardDTO dto = new FlightCardDTO();
        dto.setFlights(list);

        // verify
        assertNotNull(dto.getFlights());
        assertEquals(2, dto.getFlights().size());
        assertSame(f1, dto.getFlights().get(0));
        assertSame(f2, dto.getFlights().get(1));
    }
}
