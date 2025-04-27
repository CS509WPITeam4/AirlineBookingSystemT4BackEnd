package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    void testDeltaFlightGettersSetters() {
        DeltaFlight f = new DeltaFlight();
        f.setFlightId(10);
        f.setDepartAirport("SFO");
        f.setArriveAirport("SEA");
        f.setDepartDateTime("2025-06-01T08:00");
        f.setArriveDateTime("2025-06-01T10:00");
        f.setFlightNumber("DL1000");

        assertEquals(10, f.getFlightId());
        assertEquals("SEA", f.getArriveAirport());
        assertEquals("DL1000", f.getFlightNumber());
    }

    @Test
    void testSouthwestFlightGettersSetters() {
        SouthwestFlight f = new SouthwestFlight();
        f.setFlightId(20);
        f.setDepartAirport("ORD");
        f.setArriveAirport("MDW");
        f.setDepartDateTime("2025-07-01T07:00");
        f.setArriveDateTime("2025-07-01T07:45");
        f.setFlightNumber("WN2000");

        assertEquals(20, f.getFlightId());
        assertEquals("MDW", f.getArriveAirport());
        assertEquals("WN2000", f.getFlightNumber());
    }
}
