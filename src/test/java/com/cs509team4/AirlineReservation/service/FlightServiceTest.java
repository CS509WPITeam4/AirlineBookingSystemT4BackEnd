package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.DeltaRepository;
import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.DeltaFlight;
import com.cs509team4.AirlineReservation.SouthwestRepository;
import com.cs509team4.AirlineReservation.SouthwestFlight;
import com.cs509team4.AirlineReservation.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceTest {

    @Mock private DeltaRepository deltaRepository;
    @Mock private SouthwestRepository southwestRepository;
    @InjectMocks private FlightService flightService;

    @BeforeEach void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights() {
        DeltaFlight d = new DeltaFlight();
        SouthwestFlight s = new SouthwestFlight();
        when(deltaRepository.search("A", "B")).thenReturn(List.of(d));
        when(southwestRepository.search("A", "B")).thenReturn(List.of(s));

        List<Flight> flights = flightService.searchFlights("A", "B");
        assertEquals(2, flights.size());
    }
}