package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        Optional<? extends Flight> flight = flightRepository.findByFlightNumberAndDepartDateTime(
                booking.getFlightNumber(), booking.getDepartDateTime());

//        Optional<? extends Flight> southwestFlight = southwestRepository.findByFlightNumberAndDepartDateTime(
//                booking.getFlightNumber(), booking.getDepartDateTime());

        if (flight.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
        }

        Optional<Booking> existingBooking = bookingRepository.findByUserIdAndFlightNumber(
                booking.getUserId(), booking.getFlightNumber());

        if (existingBooking.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate booking not allowed");
        }

        booking.setStatus("Confirmed");
        Booking savedBooking = bookingRepository.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }
}
