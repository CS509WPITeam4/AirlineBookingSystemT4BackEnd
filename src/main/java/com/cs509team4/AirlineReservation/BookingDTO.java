package com.cs509team4.AirlineReservation;

import java.time.LocalDateTime;

public class BookingDTO {

    private Long id;
    private Long userId;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String status;

    public BookingDTO() {}

    public BookingDTO(Long id, Long userId, String flightNumber, String departureAirport,
                      String arrivalAirport, LocalDateTime departureDateTime,
                      LocalDateTime arrivalDateTime, String status) {
        this.id = id;
        this.userId = userId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.status = status;
    }

    public static BookingDTO fromBooking(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getUserId(),
                booking.getFlightNumber(),
                booking.getDepartureAirport(),
                booking.getArrivalAirport(),
                booking.getDepartureDateTime(),
                booking.getArrivalDateTime(),
                booking.getStatus()
        );
    }

    public Booking toBooking() {
        Booking booking = new Booking();
        booking.setId(this.id);
        booking.setUserId(this.userId);
        booking.setFlightNumber(this.flightNumber);
        booking.setDepartureAirport(this.departureAirport);
        booking.setArrivalAirport(this.arrivalAirport);
        booking.setDepartureDateTime(this.departureDateTime);
        booking.setArrivalDateTime(this.arrivalDateTime);
        booking.setStatus(this.status);
        return booking;
    }

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
