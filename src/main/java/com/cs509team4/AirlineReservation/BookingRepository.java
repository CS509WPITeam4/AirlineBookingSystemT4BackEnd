package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByUserIdAndFlightNumber(Long userId, String flightNumber);
}