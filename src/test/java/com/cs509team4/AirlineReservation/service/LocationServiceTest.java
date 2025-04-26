package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.Location;
import com.cs509team4.AirlineReservation.LocationRepository;
import com.cs509team4.AirlineReservation.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocationServiceTest {
    @Mock
    private LocationRepository locationRepository;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Pageable can be mocked or use Pageable.unpaged()
        pageable = Pageable.unpaged();
    }

    @Test
    void testGetLocations_WithQuery() {
        Location loc = new Location();
        loc.setCityName("Boston");

        when(locationRepository.searchLocations("query", pageable)).thenReturn(List.of(loc));

        List<Location> result = locationService.getLocations("query", pageable);
        assertEquals(1, result.size());
        assertEquals("Boston", result.get(0).getCityName());
    }

    @Test
    void testGetLocations_WithoutQuery() {
        Location loc = new Location();
        loc.setCityName("NYC");
        Page<Location> page = new PageImpl<>(List.of(loc));

        when(locationRepository.findAll(pageable)).thenReturn(page);

        List<Location> result = locationService.getLocations("", pageable);
        assertEquals(1, result.size());
        assertEquals("NYC", result.get(0).getCityName());
    }
}