package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                booking.getFlightNumber(), booking.getDepartureDateTime());

//        Optional<? extends Flight> southwestFlight = southwestRepository.findByFlightNumberAndDepartDateTime(
//                booking.getFlightNumber(), booking.getDepartDateTime());

        if (flight.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingRepository.save(booking));
    }

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO dto = bookingService.getBookingById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO dto) {
        try {
            BookingDTO created = bookingService.createBooking(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (DuplicateBookingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
