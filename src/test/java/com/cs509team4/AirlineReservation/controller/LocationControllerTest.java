package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Location;
import com.cs509team4.AirlineReservation.LocationController;
import com.cs509team4.AirlineReservation.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LocationControllerTest {

    @Mock private LocationService locationService;
    @InjectMocks private LocationController locationController;

    @BeforeEach void init() { MockitoAnnotations.openMocks(this); }

    @Test
    void emptyReturnsNoContent() {
        when(locationService.getLocations(any(), any(Pageable.class)))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<Location>> resp = locationController.getLocations(null, 0, 10);
        assertEquals(204, resp.getStatusCodeValue());
    }

    @Test
    void nonEmptyReturnsOk() {
        Location loc = new Location();
        loc.setCityName("NYC");
        when(locationService.getLocations("NYC", PageRequest.of(0,10)))
                .thenReturn(List.of(loc));

        ResponseEntity<List<Location>> resp = locationController.getLocations("NYC", 0, 10);
        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("NYC", resp.getBody().get(0).getCityName());
    }
}
