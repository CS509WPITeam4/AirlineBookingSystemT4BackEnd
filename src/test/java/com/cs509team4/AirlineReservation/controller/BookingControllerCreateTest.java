package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingControllerCreateTest {

    @Mock private BookingService bookingService;
    @InjectMocks private BookingController bookingController;

    @BeforeEach void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBookingSuccess() {
        BookingDTO dto = new BookingDTO();
        dto.setUserId(1L);
        dto.setFlightNumber("DL300");
        BookingDTO saved = new BookingDTO();
        saved.setUserId(1L);
        saved.setFlightNumber("DL300");

        when(bookingService.createBooking(dto)).thenReturn(saved);

        ResponseEntity<BookingDTO> resp = bookingController.createBooking(dto);
        assertEquals(201, resp.getStatusCodeValue());
        assertEquals("DL300", resp.getBody().getFlightNumber());
    }

    @Test
    void testCreateBookingDuplicateReturns409() {
        BookingDTO dto = new BookingDTO();
        dto.setUserId(1L);
        dto.setFlightNumber("DL300");

        when(bookingService.createBooking(dto))
                .thenThrow(new DuplicateBookingException("Duplicate"));

        ResponseEntity<BookingDTO> resp = bookingController.createBooking(dto);
        assertEquals(409, resp.getStatusCodeValue());
        assertNull(resp.getBody());
    }
}