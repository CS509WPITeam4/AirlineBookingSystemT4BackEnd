package com.cs509team4.AirlineReservation;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeltaRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM DeltaFlight f WHERE f.departAirport = :departAirport AND f.arriveAirport = :arriveAirport")
    List<Flight> search(String departAirport, String arriveAirport);
    @Query("SELECT f FROM DeltaFlight f WHERE f.id = :id")
    Flight getFlight(int id);

    @Query("SELECT f FROM DeltaFlight f WHERE f.flightNumber = :flightNumber AND f.departDateTime = :departDateTime")
    Optional<Flight> findByFlightNumberAndDepartDateTime(String flightNumber, java.time.LocalDateTime departDateTime);
}
