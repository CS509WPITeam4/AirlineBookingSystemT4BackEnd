package com.cs509team4.AirlineReservation;

import com.cs509team4.AirlineReservation.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE LOWER(l.cityName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(l.airportName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Location> searchLocations(@Param("query") String query, Pageable pageable);
}