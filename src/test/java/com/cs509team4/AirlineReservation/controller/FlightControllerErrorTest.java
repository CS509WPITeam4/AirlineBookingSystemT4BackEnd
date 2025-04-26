package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightControllerErrorTest {

    @Mock private DeltaRepository deltaRepository;
    @Mock private SouthwestRepository southwestRepository;
    @InjectMocks private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights_BadRequest() {
        // missing one of the params yields 400
        ResponseEntity<List<Flight>> r1 = flightController.searchFlights(null, "LAX");
        assertEquals(400, r1.getStatusCodeValue());
        ResponseEntity<List<Flight>> r2 = flightController.searchFlights("JFK", null);
        assertEquals(400, r2.getStatusCodeValue());
    }

    @Test
    void testGetFlightDetails_BadRequest() {
        // no flightNum
        ResponseEntity<Flight> r1 = flightController.getFlight(1, null);
        assertEquals(400, r1.getStatusCodeValue());
        // invalid airline prefix
        ResponseEntity<Flight> r2 = flightController.getFlight(1, "XX123");
        assertEquals(400, r2.getStatusCodeValue());
    }
}
