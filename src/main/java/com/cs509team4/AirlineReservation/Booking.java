package com.cs509team4.AirlineReservation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String flightNumber;
    private String departAirport;
    private String arriveAirport;
    private LocalDateTime departDateTime;
    private LocalDateTime arriveDateTime;
    private String status;

    public Booking() {}

    public Booking(Long userId, String flightNumber, String departAirport, String arriveAirport,
                   LocalDateTime departDateTime, LocalDateTime arriveDateTime, String status) {
        this.userId = userId;
        this.flightNumber = flightNumber;
        this.departAirport = departAirport;
        this.arriveAirport = arriveAirport;
        this.departDateTime = departDateTime;
        this.arriveDateTime = arriveDateTime;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public LocalDateTime getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(LocalDateTime departDateTime) {
        this.departDateTime = departDateTime;
    }

    public LocalDateTime getArriveDateTime() {
        return arriveDateTime;
    }

    public void setArriveDateTime(LocalDateTime arriveDateTime) {
        this.arriveDateTime = arriveDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
