package com.cs509team4.AirlineReservation;

import java.util.List;

public class SelectedFlightsDTO {
    private List<FlightDTO> departures;
    private List<FlightDTO> returns;

    public SelectedFlightsDTO() {}

    public SelectedFlightsDTO(List<FlightDTO> departures, List<FlightDTO> returns) {
        this.departures = departures;
        this.returns = returns;
    }

    public List<FlightDTO> getDepartures() {
        return departures;
    }

    public void setDepartures(List<FlightDTO> departures) {
        this.departures = departures;
    }

    public List<FlightDTO> getReturns() {
        return returns;
    }

    public void setReturns(List<FlightDTO> returns) {
        this.returns = returns;
    }
}
