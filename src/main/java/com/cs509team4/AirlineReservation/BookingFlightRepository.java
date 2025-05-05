package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingFlightRepository extends JpaRepository<BookingFlight, Long> {
    List<BookingFlight> findAllByBookingId(Long bookingId);
}
