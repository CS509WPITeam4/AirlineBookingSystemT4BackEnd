package com.cs509team4.AirlineReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(BookingDTO::fromBooking)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .map(BookingDTO::fromBooking)
                .orElse(null);
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        if (bookingRepository.existsByUserIdAndFlightNumber(
                bookingDTO.getUserId(), bookingDTO.getFlightNumber())) {
            throw new DuplicateBookingException("Duplicate booking");
        }
        Booking booking = bookingDTO.toBooking();
        Booking savedBooking = bookingRepository.save(booking);
        return BookingDTO.fromBooking(savedBooking);
    }
}
