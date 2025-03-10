package com.cs509team4.AirlineReservation;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SouthwestRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM SouthwestFlight f WHERE f.departAirport = :departAirport AND f.arriveAirport = :arriveAirport")
    List<Flight> search(String departAirport, String arriveAirport);
}
