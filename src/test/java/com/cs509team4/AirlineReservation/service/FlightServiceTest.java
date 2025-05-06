package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.FlightRepository;
import com.cs509team4.AirlineReservation.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {
    @Mock
    FlightRepository repo;
    @InjectMocks
    FlightService svc;

    @BeforeEach void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchFlights_combinesBothSources() {
        Flight f1 = mock(Flight.class), f2 = mock(Flight.class);
        when(repo.search("A","B")).thenReturn(List.of(f1, f2));
        var all = svc.searchFlights("A","B");
        assertEquals(2, all.size());
        verify(repo).search("A","B");
    }

    @Test
    void getFlightDetails_nullOrShort_throws() {
        assertThrows(IllegalArgumentException.class, () -> svc.getFlightDetails(1, null));
        assertThrows(IllegalArgumentException.class, () -> svc.getFlightDetails(1, "X"));
    }

    @Test
    void getFlightDetails_returnsRepoFlight() {
        Flight f = mock(Flight.class);
        when(repo.getFlight(123)).thenReturn(f);
        Flight out = svc.getFlightDetails(123, "DL999");
        assertSame(f, out);
        verify(repo).getFlight(123);
    }
}
