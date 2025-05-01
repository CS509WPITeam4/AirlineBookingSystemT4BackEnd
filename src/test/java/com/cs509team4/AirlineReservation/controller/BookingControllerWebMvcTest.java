package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.Flight;
import com.cs509team4.AirlineReservation.FlightRepository;
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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@Import(BookingControllerWebMvcTest.TestConfig.class)
class BookingControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        // clear any previous stubbing
        reset(flightRepository, bookingRepository);
    }

    @Test
    void createBooking_whenFlightExists_returns201() throws Exception {
        LocalDateTime dt = LocalDateTime.of(2025, 5, 1, 8, 30);
        String payload = """
            {
              "flightNumber":"DL300",
              "departureDateTime":"2025-05-01T08:30:00"
            }
            """;

        when(flightRepository
                .findByFlightNumberAndDepartDateTime(eq("DL300"), eq(dt)))
                .thenReturn(Optional.of(Mockito.mock(Flight.class)));

        when(bookingRepository.save(any(Booking.class)))
                .thenAnswer(inv -> inv.getArgument(0));


        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightNumber").value("DL300"))
                .andExpect(jsonPath("$.departureDateTime").value("2025-05-01T08:30:00"));
    }

    @Test
    void createBooking_whenFlightNotFound_returns404() throws Exception {
        when(flightRepository.findByFlightNumberAndDepartDateTime(any(), any()))
                .thenReturn(Optional.empty());

        String payload = """
            {
              "flightNumber":"ZZ999",
              "departureDateTime":"2025-05-01T08:30:00"
            }
            """;

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Flight not found"));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        BookingService bookingService() {
            return Mockito.mock(BookingService.class);
        }

        @Bean
        BookingRepository bookingRepository() {
            return Mockito.mock(BookingRepository.class);
        }

        @Bean
        FlightRepository flightRepository() {
            return Mockito.mock(FlightRepository.class);
        }
    }
}
