package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// connects backend to frontend
@CrossOrigin(origins = "http://localhost:8080") // change to whatever fronted localhost is
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    public FlightRepository flightRepository;

    // Search for flights based on departure and arrival locations
    @GetMapping("/search")
    public ResponseEntity<List<FlightCardDTO>> searchFlights(
            @RequestParam(required = false) String departAirport,
            @RequestParam(required = false) String arriveAirport,
            @RequestParam(required = false) String departureDate,
            @RequestParam(required = false) String maxLayover) {

        if (departAirport == null || arriveAirport == null) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate departDate = null;
        if (departureDate != null) {
            departDate = LocalDate.parse(departureDate);
        }

        int maxLayoverInt = 300;
        if (maxLayover != null) {
            maxLayoverInt = Integer.parseInt(maxLayover);
        }


        List<FlightCardDTO> results = new ArrayList<>();
        int numResults = 0;
        int numLayovers = 1;
        int MAX_RESULTS = 24;

        // Direct flights first
        List<Flight> directFlights;
        if(departDate == null) {
            directFlights = flightRepository.search(departAirport, arriveAirport);
        } else {
            directFlights = flightRepository.searchWithDate(departAirport, arriveAirport, departDate);
        }
        for (Flight f : directFlights) {
            if (numResults >= MAX_RESULTS) { break; }
            List<Flight> list = new ArrayList<>();
            list.add(f);
            results.add(new FlightCardDTO(list));
            numResults++;
        }

        // Layovers - search sequentially starting with 1, then 2, etc until MAX_RESULTS is reached or layovers hits 5
        while(numResults <= MAX_RESULTS && numLayovers < 5) {
            List<Object[]> layoverFlightsList;
            if(departDate == null) {
                layoverFlightsList = flightRepository.searchWithLayovers(departAirport, arriveAirport, numLayovers, MAX_RESULTS - numResults, null, maxLayoverInt);
            } else {
                layoverFlightsList = flightRepository.searchWithLayovers(departAirport, arriveAirport, numLayovers, MAX_RESULTS - numResults, departDate, maxLayoverInt);
            }
            for (Object[] row : layoverFlightsList) {
                if (numResults >= 100) break;

                List<Flight> list = new ArrayList<>();
                for(Object id : row) {
                    Long flightIdLong = (Long) id;
                    int flightId = flightIdLong.intValue();
                    Flight f = flightRepository.getFlight(flightId);
                    if(f != null) {
                        list.add(f);
                    }
                }

                results.add(new FlightCardDTO(list));
                numResults++;
            }
            numLayovers++;
        }

        return ResponseEntity.ok(results);
    }

    @GetMapping("/details")
    public ResponseEntity<Flight> getFlight(
            @RequestParam(required = false) int id,
            @RequestParam(required = false) String flightNum) {

        Flight flight;
        flight = flightRepository.getFlight(id);

        return ResponseEntity.ok(flight);
    }
}