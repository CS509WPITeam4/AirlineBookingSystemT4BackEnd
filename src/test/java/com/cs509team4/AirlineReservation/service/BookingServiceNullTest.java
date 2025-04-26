package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceNullTest {

    @Mock private BookingRepository bookingRepository;
    @InjectMocks private BookingService bookingService;

    @BeforeEach void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById(99L)).thenReturn(Optional.empty());
        BookingDTO dto = bookingService.getBookingById(99L);
        assertNull(dto, "Expect null when booking not found");
    }
}
