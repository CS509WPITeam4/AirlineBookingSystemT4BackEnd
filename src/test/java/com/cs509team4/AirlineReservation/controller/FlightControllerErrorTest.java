package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.FlightCardDTO;
import com.cs509team4.AirlineReservation.FlightController;
import com.cs509team4.AirlineReservation.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightControllerErrorTest {

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
    void testSearchFlights_DirectOk() {
        Flight f = mock(Flight.class);
        when(flightRepository.search("JFK", "LAX")).thenReturn(List.of(f));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "LAX", null, null);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals(1, resp.getBody().size());
        assertSame(f, resp.getBody().get(0).getFlights().get(0));
        verify(flightRepository).search("JFK", "LAX");
    }

    @Test
    void testGetFlight_IgnoresFlightNumAndReturnsOk() {
        Flight f = mock(Flight.class);
        when(flightRepository.getFlight(5)).thenReturn(f);

        // even with null flightNum, controller simply calls repo and returns 200
        ResponseEntity<Flight> resp = flightController.getFlight(5, null);
        assertEquals(200, resp.getStatusCodeValue());
        assertSame(f, resp.getBody());
        verify(flightRepository).getFlight(5);
    }
}
