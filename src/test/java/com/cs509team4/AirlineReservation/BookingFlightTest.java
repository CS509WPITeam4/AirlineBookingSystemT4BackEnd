package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingFlightTest {
    @Test
    void gettersAndSetters() {
        BookingFlight bf = new BookingFlight();
        bf.setFlightId(123L);
        bf.setLegType(LegType.RETURN);
        bf.setSequence(5);

        Booking b = new Booking();
        bf.setBooking(b);

        assertEquals(123L, bf.getFlightId());
        assertEquals(LegType.RETURN, bf.getLegType());
        assertEquals(5, bf.getSequence());
        assertSame(b, bf.getBooking());
    }
}
