package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingControllerGetByIdTest {

    @Mock private BookingService bookingService;
    @InjectMocks private BookingController bookingController;

    @BeforeEach void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetBookingByIdFound() {
//        BookingDTO dto = new BookingDTO();
//        dto.setId(5L);
//        when(bookingService.getBookingById(5L)).thenReturn(dto);
//
//        ResponseEntity<BookingDTO> resp = bookingController.getBookingById(5L);
//        assertEquals(200, resp.getStatusCodeValue());
//        assertEquals(5L, resp.getBody().getId());
//    }
//
//    @Test
//    void testGetBookingByIdNotFound() {
//        when(bookingService.getBookingById(99L)).thenReturn(null);
//
//        ResponseEntity<BookingDTO> resp = bookingController.getBookingById(99L);
//        assertEquals(404, resp.getStatusCodeValue());
//        assertNull(resp.getBody());
//    }
}
