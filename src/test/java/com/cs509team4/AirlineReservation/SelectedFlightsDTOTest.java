package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectedFlightsDTOTest {

    @Test
    void testNoArgConstructorAndSetters() {
        SelectedFlightsDTO dto = new SelectedFlightsDTO();

        // prepare departure list
        FlightDTO dep1 = new FlightDTO();
        dep1.setId(10);
        List<FlightDTO> departures = new ArrayList<>();
        departures.add(dep1);

        // prepare return list
        FlightDTO ret1 = new FlightDTO();
        ret1.setId(20);
        List<FlightDTO> returns = new ArrayList<>();
        returns.add(ret1);

        // set into DTO
        dto.setDepartures(departures);
        dto.setReturns(returns);

        // verify getters return exactly what was passed in
        assertSame(departures, dto.getDepartures());
        assertSame(returns, dto.getReturns());
    }

    @Test
    void testAllArgsConstructor() {
        FlightDTO dep1 = new FlightDTO();
        dep1.setId(100);
        FlightDTO ret1 = new FlightDTO();
        ret1.setId(200);

        List<FlightDTO> departures = List.of(dep1);
        List<FlightDTO> returns    = List.of(ret1);

        // use the all-args ctor
        SelectedFlightsDTO dto = new SelectedFlightsDTO(departures, returns);

        // verify
        assertSame(departures, dto.getDepartures());
        assertSame(returns,    dto.getReturns());
    }
}
