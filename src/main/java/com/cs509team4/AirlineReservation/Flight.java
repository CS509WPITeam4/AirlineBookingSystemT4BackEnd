package com.cs509team4.AirlineReservation;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq")
    @SequenceGenerator(name = "flight_seq", sequenceName = "flight_sequence", allocationSize = 1)
    @Column(name = "Id")
    private int id;

    @Column(name = "DepartDateTime")
    private String departDateTime;

    @Column(name = "ArriveDateTime")
    private String arriveDateTime;

    @Column(name = "DepartAirport")
    private String departAirport;

    @Column(name = "ArriveAirport")
    private String arriveAirport;

    @Column(name = "FlightNumber")
    private String flightNumber;

    // Getters and Setters (needed for connecting to database)
    public int getFlightId() {
        return id;
    }

    public void setFlightId(int flightId) {
        this.id = flightId;
    }

    public String getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(String departDateTime) {
        this.departDateTime = departDateTime;
    }

    public String getArriveDateTime() {
        return arriveDateTime;
    }

    public void setArriveDateTime(String arriveDateTime) {
        this.arriveDateTime = arriveDateTime;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight() {}

    public Flight(int id, String flightNumber, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime) {
        this.id = id;
        this.departDateTime = flightNumber;
        this.arriveDateTime = departureAirport;
        this.departAirport = arrivalAirport;
        this.arriveAirport = departureTime;
        this.flightNumber = arrivalTime;
    }
}
