package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.FlightCardDTO;
import com.cs509team4.AirlineReservation.FlightController;
import com.cs509team4.AirlineReservation.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightControllerErrorTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights_BadRequest() {
        // missing departAirport → 400
        ResponseEntity<List<FlightCardDTO>> r1 =
                flightController.searchFlights(null, "LAX", null, null);
        assertEquals(400, r1.getStatusCodeValue());

        // missing arriveAirport → 400
        ResponseEntity<List<FlightCardDTO>> r2 =
                flightController.searchFlights("JFK", null, null, null);
        assertEquals(400, r2.getStatusCodeValue());
    }

    @Test
    void testGetFlightDetails_BadRequest() {
        // missing flightNum → 400
        ResponseEntity<Flight> r1 = flightController.getFlight(1, null);
        assertEquals(400, r1.getStatusCodeValue());
    }

    @Test
    void testGetFlightDetails_Ok() {
        // with a non-null flightNum, it should call repo and return 200
        Flight mockFlight = mock(Flight.class);
        when(flightRepository.getFlight(42)).thenReturn(mockFlight);

        ResponseEntity<Flight> r2 = flightController.getFlight(42, "DL123");
        assertEquals(200, r2.getStatusCodeValue());
        assertSame(mockFlight, r2.getBody());
    }
}
