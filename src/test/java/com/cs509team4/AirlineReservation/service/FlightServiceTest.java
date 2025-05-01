package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock private FlightRepository flightRepository;
    @InjectMocks private FlightService flightService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlights_AggregatesRepo() {
        Flight f1 = new Flight(), f2 = new Flight();
        when(flightRepository.search("AAA","BBB"))
                .thenReturn(List.of(f1, f2));

        List<Flight> out = flightService.searchFlights("AAA","BBB");
        assertEquals(2, out.size());
        assertSame(f1, out.get(0));
        assertSame(f2, out.get(1));
    }

    @Test
    void testGetFlightDetails_InvalidFlightNum() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> flightService.getFlightDetails(1, "X")
        );
        assertTrue(ex.getMessage().contains("Invalid flight number"));
    }

    @Test
    void testGetFlightDetails_UsesRepo() {
        Flight f = new Flight();
        when(flightRepository.getFlight(42)).thenReturn(f);

        Flight out = flightService.getFlightDetails(42, "DL123");
        assertSame(f, out);
    }
}
