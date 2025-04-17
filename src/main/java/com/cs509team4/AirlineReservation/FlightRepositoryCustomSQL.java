package com.cs509team4.AirlineReservation;

import java.util.List;

public interface FlightRepositoryCustomSQL {
    List<Object[]> searchWithLayovers(String departAirport, String arriveAirport, int numLayovers, int max);
}
