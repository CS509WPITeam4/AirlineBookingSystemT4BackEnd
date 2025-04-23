package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        BookingDTO booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}