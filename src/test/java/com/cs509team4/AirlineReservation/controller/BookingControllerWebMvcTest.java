package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
@Import(BookingControllerWebMvcTest.TestConfig.class)
class BookingControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        Mockito.reset(bookingService);
    }

    @Test
    void duplicateReturns409() throws Exception {
        when(bookingService.createBooking(any()))
                .thenThrow(new DuplicateBookingException("Duplicate booking"));

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isConflict());
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
