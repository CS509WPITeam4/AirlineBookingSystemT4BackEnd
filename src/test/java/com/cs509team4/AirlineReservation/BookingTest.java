package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    void addBookingFlight_setsBidirectional() {
        Booking b = new Booking();
        BookingFlight bf = new BookingFlight();
        bf.setFlightId(42L);
        bf.setLegType(LegType.DEPARTURE);
        bf.setSequence(7);

        b.addBookingFlight(bf);

        List<BookingFlight> list = b.getBookingFlights();
        assertEquals(1, list.size());
        assertSame(b, bf.getBooking());
        assertEquals(42L, list.get(0).getFlightId());
    }
}
