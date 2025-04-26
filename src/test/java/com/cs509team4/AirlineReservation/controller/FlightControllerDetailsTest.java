package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightControllerDetailsTest {

    @Mock private DeltaRepository deltaRepository;
    @Mock private SouthwestRepository southwestRepository;
    @InjectMocks private FlightController flightController;

    @BeforeEach void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightDetails_Delta() {
        Flight f = new DeltaFlight();
        f.setFlightId(1);
        when(deltaRepository.getFlight(1)).thenReturn(f);

        ResponseEntity<Flight> resp = flightController.getFlight(1, "DL123");
        assertEquals(200, resp.getStatusCodeValue());
        assertSame(f, resp.getBody());
    }

    @Test
    void testGetFlightDetails_Southwest() {
        Flight f = new SouthwestFlight();
        f.setFlightId(2);
        when(southwestRepository.getFlight(2)).thenReturn(f);

        ResponseEntity<Flight> resp = flightController.getFlight(2, "WN456");
        assertEquals(200, resp.getStatusCodeValue());
        assertSame(f, resp.getBody());
    }

    @Test
    void testGetFlightDetails_BadRequest() {
        // missing flightNum
        ResponseEntity<Flight> resp1 = flightController.getFlight(0, null);
        assertEquals(400, resp1.getStatusCodeValue());

        // invalid airline prefix
        ResponseEntity<Flight> resp2 = flightController.getFlight(0, "XX000");
        assertEquals(400, resp2.getStatusCodeValue());
    }
}
