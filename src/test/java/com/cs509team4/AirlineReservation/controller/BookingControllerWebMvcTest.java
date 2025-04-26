package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    private BookingService bookingService;  // This is the mock from TestConfig

    @BeforeEach
    void setUp() {
        // Reset stubs before each test
        Mockito.reset(bookingService);
    }

    @Test
    void duplicateReturns409() throws Exception {
        // Arrange: stub the service to throw on createBooking
        when(bookingService.createBooking(any()))
                .thenThrow(new DuplicateBookingException("dup"));

        // Act & Assert: POSTing to /api/bookings yields 409 Conflict
        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isConflict());
    }

    /**
     * Test configuration to supply a mock BookingService bean.
     * This replaces the deprecated @MockBean approach.
     */
    static class TestConfig {
        @Bean
        public BookingService bookingService() {
            return Mockito.mock(BookingService.class);
        }
    }
}
