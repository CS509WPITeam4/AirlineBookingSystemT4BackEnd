package com.cs509team4.AirlineReservation;

import java.time.LocalDateTime;

public class BookingDTO {

    private Long id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String status;

    // Default constructor
    public BookingDTO() {
    }

    // Parameterized constructor
    public BookingDTO(Long id, String flightNumber, String departureAirport, String arrivalAirport,
                      LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, String status) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.status = status;
    }

    // Static method to convert Booking to BookingDTO
    public static BookingDTO fromBooking(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getFlightNumber(),
                booking.getDepartureAirport(),
                booking.getArrivalAirport(),
                booking.getDepartureDateTime(),
                booking.getArrivalDateTime(),
                booking.getStatus()
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}