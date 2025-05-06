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

class FlightControllerDetailsTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFlight_alwaysReturns200_andBodyFromRepository() {
        Flight flight = mock(Flight.class);
        when(flightRepository.getFlight(42)).thenReturn(flight);

        // regardless of the flightNum value, should return 200 + the flight
        ResponseEntity<Flight> resp1 = flightController.getFlight(42, "DL123");
        ResponseEntity<Flight> resp2 = flightController.getFlight(42, null);

        assertEquals(200, resp1.getStatusCodeValue());
        assertSame(flight, resp1.getBody());

        assertEquals(200, resp2.getStatusCodeValue());
        assertSame(flight, resp2.getBody());

        verify(flightRepository, times(2)).getFlight(42);
    }
}
