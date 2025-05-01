package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeltaRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f WHERE f.departAirport = :departAirport AND f.arriveAirport = :arriveAirport")
    List<Flight> search(String departAirport, String arriveAirport);

    @Query("SELECT f FROM Flight f WHERE f.id = :id")
    Flight getFlight(int id);
}
