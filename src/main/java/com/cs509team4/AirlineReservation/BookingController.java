package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createBooking(@RequestBody SelectedFlightsDTO selectedFlights) {
        Booking booking = bookingService.createBooking(
                selectedFlights.getDepartures(),
                selectedFlights.getReturns()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(booking.getId());
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId) {
//        List<BookingDTO> bookings = bookingService.getUserBookings(userId);
//        return ResponseEntity.ok(bookings);
//    }
//
//    @GetMapping("/{bookingId}")
//    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
//        BookingDTO booking = bookingService.getBookingById(bookingId);
//        if (booking != null) {
//            return ResponseEntity.ok(booking);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}