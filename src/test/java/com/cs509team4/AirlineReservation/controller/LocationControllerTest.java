package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Location;
import com.cs509team4.AirlineReservation.LocationController;
import com.cs509team4.AirlineReservation.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testGetLocationsWithNoQuery() {
        Location loc = new Location();
        loc.setCityName("Boston");

        // Mock service with no query and unpaged
        when(locationService.getLocations(null, Pageable.unpaged()))
                .thenReturn(List.of(loc));

        // Call controller
        ResponseEntity<List<Location>> response =
                locationController.getLocations(null, Pageable.unpaged());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Boston", response.getBody().get(0).getCityName());
    }

    @Test
    void testGetLocationsWithQuery() {
        Location loc = new Location();
        loc.setCityName("NYC");

        when(locationService.getLocations("NYC", Pageable.unpaged()))
                .thenReturn(List.of(loc));

        ResponseEntity<List<Location>> response =
                locationController.getLocations("NYC", Pageable.unpaged());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("NYC", response.getBody().get(0).getCityName());
    }
}
