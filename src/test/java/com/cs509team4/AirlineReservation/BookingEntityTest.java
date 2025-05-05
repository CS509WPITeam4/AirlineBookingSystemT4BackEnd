package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingEntityTest {
    @Test
    void addBookingFlightMaintainsBidirectionalLink() {
        Booking booking = new Booking();
        BookingFlight bf = new BookingFlight();
        booking.addBookingFlight(bf);

        assertTrue(booking.getBookingFlights().contains(bf));
        assertSame(booking, bf.getBooking());
    }
}
