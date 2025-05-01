package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightControllerUnitTest {

    @Mock FlightRepository flightRepository;
    @InjectMocks FlightController flightController;

    @BeforeEach void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchFlights_directOnly_returnsFlightCards() {
        Flight f = new Flight();
        f.setFlightNumber("DL100");
        when(flightRepository.search("JFK","SFO"))
                .thenReturn(List.of(f));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK","SFO", null, null);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("DL100",
                resp.getBody().get(0).getFlights().get(0).getFlightNumber());
    }

    @Test
    void searchFlights_withDate_usesDateSearch() {
        LocalDate date = LocalDate.of(2025,2,2);
        Flight f = new Flight();
        f.setFlightNumber("DL200");
        when(flightRepository.searchWithDate("JFK","SFO",date))
                .thenReturn(List.of(f));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK","SFO", date.toString(), null);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("DL200",
                resp.getBody().get(0).getFlights().get(0).getFlightNumber());
    }

    @Test
    void getFlightDetails_whenNoFlightNum_returns400() {
        ResponseEntity<Flight> resp =
                flightController.getFlight(1, null);

        assertEquals(400, resp.getStatusCodeValue());
    }
}
