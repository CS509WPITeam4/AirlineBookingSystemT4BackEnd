package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocations(String query, Pageable pageable) {
        if (query != null && !query.isEmpty()) {
            return locationRepository.searchLocations(query, pageable);
        }
        return locationRepository.findAll(pageable).getContent();
    }

    public Object getLocations() {
        return locationRepository.findAll();
    }
}