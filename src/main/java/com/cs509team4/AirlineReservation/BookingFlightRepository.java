package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFlightRepository extends JpaRepository<BookingFlight, Long> {
}
