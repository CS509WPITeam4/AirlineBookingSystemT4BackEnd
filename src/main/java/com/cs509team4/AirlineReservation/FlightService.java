package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final DeltaRepository deltaRepository;
    private final SouthwestRepository southwestRepository;

    @Autowired
    public FlightService(DeltaRepository deltaRepository, SouthwestRepository southwestRepository) {
        this.deltaRepository = deltaRepository;
        this.southwestRepository = southwestRepository;
    }

    public List<Flight> searchFlights(String departAirport, String arriveAirport) {
        List<Flight> deltaFlights = deltaRepository.search(departAirport, arriveAirport);
        List<Flight> southwestFlights = southwestRepository.search(departAirport, arriveAirport);

        List<Flight> allFlights = new ArrayList<>();
        allFlights.addAll(deltaFlights);
        allFlights.addAll(southwestFlights);

        return allFlights;
    }

    public Flight getFlightDetails(int id, String flightNum) {
        if (flightNum == null || flightNum.length() < 2) {
            throw new IllegalArgumentException("Invalid flight number");
        }

        String airline = flightNum.substring(0, 2);
        if ("DL".equals(airline)) {
            return deltaRepository.getFlight(id);
        } else if ("WN".equals(airline)) {
            return southwestRepository.getFlight(id);
        } else {
            throw new IllegalArgumentException("Unsupported airline");
        }
    }
}
