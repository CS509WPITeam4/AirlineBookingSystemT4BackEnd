package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// connects backend to frontend
@CrossOrigin(origins = "http://localhost:8080") // change to whatever fronted localhost is
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private DeltaRepository deltaRepository;

    @Autowired
    private SouthwestRepository southwestRepository;

    // Search for flights based on departure and arrival locations
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam(required = false) String departAirport,
            @RequestParam(required = false) String arriveAirport) {

        if (departAirport == null || arriveAirport == null) {
            return ResponseEntity.badRequest().build();
        }

        // Fetch flights from Delta and Southwest repositories
        List<Flight> deltaFlights = deltaRepository.search(departAirport, arriveAirport);
        List<Flight> southwestFlights = southwestRepository.search(departAirport, arriveAirport);

        // Combine the two lists into one
        List<Flight> allFlights = new ArrayList<>();
        allFlights.addAll(deltaFlights);
        allFlights.addAll(southwestFlights);
        return ResponseEntity.ok(allFlights);
    }
}
