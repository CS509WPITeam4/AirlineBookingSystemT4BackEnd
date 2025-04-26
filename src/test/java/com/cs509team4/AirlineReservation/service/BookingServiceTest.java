package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserBookings() {
        Booking booking = new Booking(1L, "DL101", "JFK", "SFO",
                LocalDateTime.now(), LocalDateTime.now().plusHours(6), "CONFIRMED");

        when(bookingRepository.findByUserId(1L)).thenReturn(List.of(booking));

        List<BookingDTO> bookings = bookingService.getUserBookings(1L);
        assertEquals(1, bookings.size());
        assertEquals("DL101", bookings.get(0).getFlightNumber());
    }
}
