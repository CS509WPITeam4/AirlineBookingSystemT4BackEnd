package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

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
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(bookingRepository, flightRepository);
    }

    @Test
    void createBooking_whenFlightExists_savesAndReturns201() {
        Booking booking = new Booking();
        booking.setFlightNumber("DL300");
        booking.setDepartureDateTime(LocalDateTime.of(2025, 1, 1, 10, 0));

        // stub the flight‐lookup
        when(flightRepository.findByFlightNumberAndDepartDateTime(
                booking.getFlightNumber(),
                booking.getDepartureDateTime()))
                .thenReturn(Optional.of(new Flight())); // dummy Flight

        // stub the save
        when(bookingRepository.save(booking)).thenReturn(booking);

        ResponseEntity<?> resp = bookingController.createBooking(booking);
        assertEquals(201, resp.getStatusCodeValue());
        assertSame(booking, resp.getBody());
    }

    @Test
    void createBooking_whenFlightMissing_returns404() {
        Booking booking = new Booking();
        booking.setFlightNumber("DL300");
        booking.setDepartureDateTime(LocalDateTime.of(2025, 1, 1, 10, 0));

        // no flight found
        when(flightRepository.findByFlightNumberAndDepartDateTime(
                booking.getFlightNumber(),
                booking.getDepartureDateTime()))
                .thenReturn(Optional.empty());

        ResponseEntity<?> resp = bookingController.createBooking(booking);
        assertEquals(404, resp.getStatusCodeValue());
        assertEquals("Flight not found", resp.getBody());
    }

    /**
     * Test configuration that supplies mocks for every dependency
     * of BookingController (including the unused BookingService).
     */
    static class TestConfig {
        @Bean BookingRepository bookingRepository() {
            return Mockito.mock(BookingRepository.class);
        }
        @Bean FlightRepository flightRepository() {
            return Mockito.mock(FlightRepository.class);
        }
        @Bean BookingService bookingService() {
            return Mockito.mock(BookingService.class);
        }
    }
}
