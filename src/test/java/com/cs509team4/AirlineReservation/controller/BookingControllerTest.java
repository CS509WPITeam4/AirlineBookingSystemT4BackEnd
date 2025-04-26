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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookingsByUserId() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setFlightNumber("DL101");
        bookingDTO.setStatus("CONFIRMED");

        when(bookingService.getUserBookings(1L)).thenReturn(List.of(bookingDTO));

        ResponseEntity<List<BookingDTO>> response = bookingController.getBookingsByUserId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("DL101", response.getBody().get(0).getFlightNumber());
    }
}
