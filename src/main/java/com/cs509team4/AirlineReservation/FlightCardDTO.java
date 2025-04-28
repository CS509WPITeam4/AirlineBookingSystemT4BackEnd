package com.cs509team4.AirlineReservation;

import java.util.List;

public class FlightCardDTO {
    private List<Flight> flights;
    private String flightNumber;
    private String departAirport;
    private String arriveAirport;
    private double price;

    // No-arg constructor for tests
    public FlightCardDTO() {
    }

    // Constructor for flight list usage
    public FlightCardDTO(List<Flight> flights) {
        this.flights = flights;
    }

    // Constructor for individual flight card
    public FlightCardDTO(String flightNumber, String departAirport, String arriveAirport, double price) {
        this.flightNumber = flightNumber;
        this.departAirport = departAirport;
        this.arriveAirport = arriveAirport;
        this.price = price;
    }

    // Flights list getters/setters
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    // Individual flight getters/setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(String departAirport) {
        this.departAirport = departAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
