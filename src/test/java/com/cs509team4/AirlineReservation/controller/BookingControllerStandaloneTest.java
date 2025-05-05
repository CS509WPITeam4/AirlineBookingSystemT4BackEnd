package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import com.cs509team4.AirlineReservation.SelectedFlightsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerStandaloneTest {

    @Mock BookingService bookingService;
    @InjectMocks BookingController bookingController;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookingController)
                .setControllerAdvice(new TestExceptionHandler())
                .build();
    }

    @Test
    void createBooking_success_returns201AndBodyId() throws Exception {
        Booking fake = new Booking();
        fake.setId(123L);
        when(bookingService.createBooking(anyList(), anyList()))
                .thenReturn(fake);

        SelectedFlightsDTO dto = new SelectedFlightsDTO();
        dto.setDepartures(List.of());
        dto.setReturns(List.of());

        mockMvc.perform(post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("123"));
    }

    @Test
    void createBooking_duplicateBooking_respondsConflict() throws Exception {
        when(bookingService.createBooking(anyList(), anyList()))
                .thenThrow(new DuplicateBookingException("dup"));

        SelectedFlightsDTO dto = new SelectedFlightsDTO();
        dto.setDepartures(List.of());
        dto.setReturns(List.of());

        mockMvc.perform(post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }
    @ControllerAdvice
    static class TestExceptionHandler {
        @ExceptionHandler(DuplicateBookingException.class)
        public ResponseEntity<Void> handleDup(DuplicateBookingException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
