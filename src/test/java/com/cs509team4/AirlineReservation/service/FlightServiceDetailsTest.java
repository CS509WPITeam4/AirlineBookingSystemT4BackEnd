package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceDetailsTest {

    @Mock private DeltaRepository deltaRepository;
    @Mock private SouthwestRepository southwestRepository;
    @InjectMocks private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightDetails_Delta() {
        DeltaFlight df = new DeltaFlight();
        df.setFlightId(1);
        when(deltaRepository.getFlight(1)).thenReturn(df);

        Flight result = flightService.getFlightDetails(1, "DL123");
        assertSame(df, result);
    }

    @Test
    void testGetFlightDetails_Southwest() {
        SouthwestFlight sf = new SouthwestFlight();
        sf.setFlightId(2);
        when(southwestRepository.getFlight(2)).thenReturn(sf);

        Flight result = flightService.getFlightDetails(2, "WN456");
        assertSame(sf, result);
    }

    @Test
    void testGetFlightDetails_InvalidAirline() {
        assertThrows(IllegalArgumentException.class,
                () -> flightService.getFlightDetails(3, "XX789"));
    }

    @Test
    void testGetFlightDetails_NullFlightNum() {
        assertThrows(IllegalArgumentException.class,
                () -> flightService.getFlightDetails(4, null));
    }
}
