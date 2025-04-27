package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Location;
import com.cs509team4.AirlineReservation.LocationController;
import com.cs509team4.AirlineReservation.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void emptyReturns204NoContent() {
        when(locationService.getLocations(anyString(), any(Pageable.class)))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<Location>> resp = locationController.getLocations(null, 0, 10);
        assertEquals(204, resp.getStatusCodeValue());
        assertNull(resp.getBody());
    }

    @Test
    void nonEmptyReturns200Ok() {
        Location loc = new Location("LAX", "Los Angeles", "USA", "Los Angeles Intl");

        // use matchers for both args—no raw values mixed in
        when(locationService.getLocations(eq("LAX"), any(Pageable.class)))
                .thenReturn(List.of(loc));

        ResponseEntity<List<Location>> resp = locationController.getLocations("LAX", 0, 10);
        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals(1, resp.getBody().size());

        Location returned = resp.getBody().get(0);
        assertEquals("LAX", returned.getIataCode());
        assertEquals("Los Angeles", returned.getCityName());
        assertEquals("USA", returned.getCountry());
        assertEquals("Los Angeles Intl", returned.getAirportName());
    }
}
