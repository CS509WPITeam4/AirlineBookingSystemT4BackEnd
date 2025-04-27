package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {
                BookingController.class,
                BookingControllerCreateTest.TestConfig.class
        }
)
class BookingControllerCreateTest {

    @Autowired
    private BookingController bookingController;

    @Autowired
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        Mockito.reset(bookingService);
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

        ResponseEntity<?> resp = bookingController.createBooking(dto);
        assertEquals(201, resp.getStatusCodeValue());
        assertEquals("DL300", ((BookingDTO)resp.getBody()).getFlightNumber());
    }

    @Test
    void testCreateBookingDuplicate() {
        BookingDTO dto = new BookingDTO();
        dto.setUserId(1L);
        dto.setFlightNumber("DL300");

        when(bookingService.createBooking(dto))
                .thenThrow(new DuplicateBookingException("dup"));

        ResponseEntity<?> resp = bookingController.createBooking(dto);
        assertEquals(409, resp.getStatusCodeValue());
        assertNull(resp.getBody());
    }

    @TestConfiguration
    static class TestConfig {
        @Bean BookingService bookingService() {
            return Mockito.mock(BookingService.class);
        }
        @Bean BookingRepository bookingRepository() {
            return Mockito.mock(BookingRepository.class);
        }
    }
}
