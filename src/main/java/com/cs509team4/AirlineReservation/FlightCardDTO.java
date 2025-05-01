package com.cs509team4.AirlineReservation;

import java.util.List;

public class FlightCardDTO {
    private List<Flight> flights;

    // no-arg ctor for frameworks/tests
    public FlightCardDTO() {
    }

    // all-args ctor
    public FlightCardDTO(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}