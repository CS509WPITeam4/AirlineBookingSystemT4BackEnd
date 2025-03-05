package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


// connects backend to frontend
@CrossOrigin(origins = "http://localhost:8080") // change to whatever fronted localhost is
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // GET /api/flights/{flightID}
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int flightId) {
        Optional<Flight> flight = flightService.getFlightById(flightId);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
