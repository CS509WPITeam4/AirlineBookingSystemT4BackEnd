package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingFlightTest {

    @Test
    void settersAndGetters_work() {
        BookingFlight bf = new BookingFlight();
        bf.setFlightId(42L);
        bf.setLegType(LegType.DEPARTURE);
        bf.setSequence(7);

        Booking booking = new Booking();
        bf.setBooking(booking);

        assertEquals(42L, bf.getFlightId());
        assertEquals(LegType.DEPARTURE, bf.getLegType());
        assertEquals(7, bf.getSequence());
        assertSame(booking, bf.getBooking());
    }
}
