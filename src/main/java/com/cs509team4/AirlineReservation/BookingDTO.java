package com.cs509team4.AirlineReservation;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {

    private List<Long> departures;
    private List<Long> returns;

    public BookingDTO() {}

    public BookingDTO(List<Long> departures, List<Long> returns) {
        this.departures = departures;
        this.returns = returns;
    }

    public List<Long> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Long> departures) {
        this.departures = departures;
    }

    public List<Long> getReturns() {
        return returns;
    }

    public void setReturns(List<Long> returns) {
        this.returns = returns;
    }
}
