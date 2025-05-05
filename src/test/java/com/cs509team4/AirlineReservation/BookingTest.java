package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    void testIdAndBookingFlights() {
        Booking booking = new Booking();
        assertNull(booking.getId());
        BookingFlight bf = new BookingFlight();
        bf.setFlightId(99L);
        bf.setLegType(LegType.DEPARTURE);
        booking.addBookingFlight(bf);

        // after adding, bookingFlights contains bf and bf.getBooking()==booking
        assertEquals(1, booking.getBookingFlights().size());
        assertSame(booking, booking.getBookingFlights().get(0).getBooking());
    }

    @Test
    void testParamCtor() {
        BookingFlight bf = new BookingFlight();
        Booking booking = new Booking(123L, List.of(bf));
        assertEquals(123L, booking.getId());
        assertEquals(1, booking.getBookingFlights().size());
    }
}
