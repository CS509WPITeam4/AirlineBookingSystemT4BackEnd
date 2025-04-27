package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FlightControllerTest {

    private FlightController flightController;

    @Mock
    private DeltaRepository deltaRepository;

    @Mock
    private SouthwestRepository southwestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightController = new FlightController();
        flightController.deltaRepository = deltaRepository;
        flightController.southwestRepository = southwestRepository;
    }

    @Test
    void testSearchFlights() {
        Flight deltaFlight = new DeltaFlight();  // Assuming you have subclasses
        Flight swFlight = new SouthwestFlight();

        when(deltaRepository.search("JFK", "SFO")).thenReturn(List.of(deltaFlight));
        when(southwestRepository.search("JFK", "SFO")).thenReturn(List.of(swFlight));

        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "SFO");
        assertEquals(2, response.getBody().size());
    }
}