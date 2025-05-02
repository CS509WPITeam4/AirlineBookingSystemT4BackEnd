package com.cs509team4.AirlineReservation;

public class FlightDTO {
    private int id;

    private String departDateTime;
    private String arriveDateTime;

    public FlightDTO() {}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}