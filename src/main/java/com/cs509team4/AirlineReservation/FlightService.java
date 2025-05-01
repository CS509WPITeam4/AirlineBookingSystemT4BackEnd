package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String departAirport, String arriveAirport) {
        return flightRepository.search(departAirport, arriveAirport);
    }

    public Flight getFlightDetails(int id, String flightNum) {
        if (flightNum == null || flightNum.length() < 2) {
            throw new IllegalArgumentException("Invalid flight number");
        }
        return flightRepository.getFlight(id);
    }
}
