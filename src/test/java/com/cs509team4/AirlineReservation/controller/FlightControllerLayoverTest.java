package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.FlightController;
import com.cs509team4.AirlineReservation.FlightRepository;
import com.cs509team4.AirlineReservation.FlightCardDTO;
import com.cs509team4.AirlineReservation.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class FlightControllerLayoverTest {

    @Mock
    private FlightRepository flightRepository;

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightController = new FlightController();
        // inject the mock into the private field
        ReflectionTestUtils.setField(flightController, "flightRepository", flightRepository);
    }

    @Test
    void testSearchFlights_Layovers() {
        Long[] route = new Long[]{101L, 202L};
        int MAX_RESULTS = 24;
        int maxLayoverInt = 300;

        // stub direct flights to empty
        when(flightRepository.search("JFK", "LAX"))
                .thenReturn(List.of());

        // stub layover query to return our one‐row route
        when(flightRepository.searchWithLayovers(
                eq("JFK"),
                eq("LAX"),
                eq(1),
                eq(MAX_RESULTS),
                isNull(LocalDate.class),
                eq(maxLayoverInt)
        )).thenReturn(List.<Object[]>of(route));

        // stub fetching each flight by ID
        Flight f1 = new Flight(); // or a Mockito.mock(Flight.class)
        Flight f2 = new Flight();
        when(flightRepository.getFlight(101)).thenReturn(f1);
        when(flightRepository.getFlight(202)).thenReturn(f2);

        // call the controller
        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "LAX", null, null);

        assertEquals(200, resp.getStatusCodeValue());
        List<FlightCardDTO> cards = resp.getBody();
        assertNotNull(cards);
        assertEquals(1, cards.size());

        List<Flight> flights = cards.get(0).getFlights();
        assertEquals(2, flights.size());
        // verify both legs are in the returned card
        assertTrue(flights.contains(f1) && flights.contains(f2));
    }
}
