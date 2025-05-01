package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {
                FlightController.class,
                FlightControllerSearchTest.TestConfig.class
        }
)
class FlightControllerSearchTest {

    @Autowired
    private FlightController flightController;

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(flightRepository);
    }

    @Test
    void searchFlights_directOnly_returnsOneCard() {
        Flight f1 = new Flight();
        f1.setFlightNumber("DL100");
        when(flightRepository.search("JFK", "SFO"))
                .thenReturn(List.of(f1));

        // call without date or layover params
        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "SFO", null, null);

        assertEquals(200, resp.getStatusCodeValue());
        List<FlightCardDTO> cards = resp.getBody();
        assertNotNull(cards);
        assertEquals(1, cards.size());
        // inside the first card, exactly our one Flight:
        assertEquals("DL100", cards.get(0).getFlights().get(0).getFlightNumber());
    }

    @Test
    void searchFlights_withDate_usesDateSearch() {
        LocalDate d = LocalDate.of(2025, 2, 2);
        Flight f2 = new Flight();
        f2.setFlightNumber("DL200");
        when(flightRepository.searchWithDate("JFK", "SFO", d))
                .thenReturn(List.of(f2));

        ResponseEntity<List<FlightCardDTO>> resp =
                flightController.searchFlights("JFK", "SFO", d.toString(), null);

        assertEquals(200, resp.getStatusCodeValue());
        List<FlightCardDTO> cards = resp.getBody();
        assertNotNull(cards);
        assertEquals(1, cards.size());
        assertEquals("DL200", cards.get(0).getFlights().get(0).getFlightNumber());
    }

    static class TestConfig {
        @Bean FlightRepository flightRepository() {
            return Mockito.mock(FlightRepository.class);
        }
    }
}
