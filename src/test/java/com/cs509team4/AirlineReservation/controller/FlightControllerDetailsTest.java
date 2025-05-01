package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.FlightController;
import com.cs509team4.AirlineReservation.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightControllerDetailsTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightDetails_BadRequest() {
        // No flightNum → 400
        ResponseEntity<Flight> resp = flightController.getFlight(5, null);
        assertEquals(400, resp.getStatusCodeValue());
    }

    @Test
    void testGetFlightDetails_Ok() {
        Flight flight = mock(Flight.class);
        when(flightRepository.getFlight(5)).thenReturn(flight);

        ResponseEntity<Flight> resp = flightController.getFlight(5, "DL100");
        assertEquals(200, resp.getStatusCodeValue());
        assertSame(flight, resp.getBody());

        verify(flightRepository).getFlight(5);
    }
}
