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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightControllerUnitTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchFlights_directOnly_returnsFlightCards() {
        Flight f = new Flight();
        f.setFlightNumber("DL100");
        when(flightRepository.search("JFK", "SFO"))
                .thenReturn(List.of(f));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "SFO", null, null);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals(1, resp.getBody().size());
        assertEquals("DL100", resp.getBody()
                .get(0)
                .getFlights()
                .get(0)
                .getFlightNumber());
        verify(flightRepository).search("JFK", "SFO");
    }

    @Test
    void searchFlights_withDate_usesDateSearch() {
        LocalDate date = LocalDate.of(2025, 2, 2);
        Flight f = new Flight();
        f.setFlightNumber("DL200");
        when(flightRepository.searchWithDate("JFK", "SFO", date))
                .thenReturn(List.of(f));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "SFO", date.toString(), null);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("DL200", resp.getBody()
                .get(0)
                .getFlights()
                .get(0)
                .getFlightNumber());
        verify(flightRepository).searchWithDate("JFK", "SFO", date);
    }

    @Test
    void searchFlights_layover_combinesRowsIntoFlightCardDTOs() {
        // no direct flights
        when(flightRepository.search("JFK", "LAX"))
                .thenReturn(Collections.emptyList());

        // one layover row [1L, 2L]
        Object[] row = new Object[]{1L, 2L};
        when(flightRepository.searchWithLayovers(
                eq("JFK"), eq("LAX"),
                eq(1), eq(24), isNull(), eq(300)))
                .thenReturn(Collections.singletonList(row));

        // repository returns Flight for both IDs
        Flight f1 = mock(Flight.class), f2 = mock(Flight.class);
        when(flightRepository.getFlight(1)).thenReturn(f1);
        when(flightRepository.getFlight(2)).thenReturn(f2);

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "LAX", null, null);

        List<FlightCardDTO> cards = resp.getBody();
        assertEquals(1, cards.size());
        List<Flight> flights = cards.get(0).getFlights();
        assertEquals(2, flights.size());
        assertSame(f1, flights.get(0));
        assertSame(f2, flights.get(1));

        verify(flightRepository).search("JFK", "LAX");
        verify(flightRepository)
                .searchWithLayovers("JFK", "LAX", 1, 24, null, 300);
        verify(flightRepository).getFlight(1);
        verify(flightRepository).getFlight(2);
    }

    @Test
    void getFlightDetails_alwaysReturnsOk_andDelegatesToRepo() {
        Flight f = mock(Flight.class);
        when(flightRepository.getFlight(123)).thenReturn(f);

        ResponseEntity<Flight> resp = flightController.getFlight(123, "ANY");
        assertEquals(200, resp.getStatusCodeValue());
        assertSame(f, resp.getBody());
        verify(flightRepository).getFlight(123);
    }
}
