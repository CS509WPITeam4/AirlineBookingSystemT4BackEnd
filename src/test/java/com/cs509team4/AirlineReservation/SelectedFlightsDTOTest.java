package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SelectedFlightsDTOTest {
    @Test
    void allArgsCtorAndGetters() {
        FlightDTO a = new FlightDTO(); a.setId(1);
        FlightDTO b = new FlightDTO(); b.setId(2);
        SelectedFlightsDTO dto = new SelectedFlightsDTO(List.of(a), List.of(b));
        assertEquals(1, dto.getDepartures().get(0).getId());
        assertEquals(2, dto.getReturns().get(0).getId());
    }

    @Test
    void noArgCtorAndSetters() {
        SelectedFlightsDTO dto = new SelectedFlightsDTO();
        FlightDTO a = new FlightDTO(); a.setId(5);
        FlightDTO b = new FlightDTO(); b.setId(6);
        dto.setDepartures(List.of(a,b));
        dto.setReturns   (List.of(b));
        assertEquals(List.of(5,6), dto.getDepartures().stream().map(FlightDTO::getId).toList());
        assertEquals(List.of(6),   dto.getReturns().stream().map(FlightDTO::getId).toList());
    }
}
