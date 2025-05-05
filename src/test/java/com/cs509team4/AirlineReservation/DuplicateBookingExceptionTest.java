package com.cs509team4.AirlineReservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuplicateBookingExceptionTest {
    @Test
    void messageIsPreserved() {
        DuplicateBookingException ex = new DuplicateBookingException("dupe!");
        assertEquals("dupe!", ex.getMessage());
    }
}
