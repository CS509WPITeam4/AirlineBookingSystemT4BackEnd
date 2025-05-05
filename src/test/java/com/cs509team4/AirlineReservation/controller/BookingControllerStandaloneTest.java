package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookingControllerStandaloneTest {

    @Mock
    BookingService bookingService;
    @InjectMocks
    BookingController bookingController;
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(bookingController)
                .setControllerAdvice(new RestExceptionHandler()) // if you have one,
                .build();
    }

    @Test
    void createBooking_happyPath_returns201_withId() throws Exception {
        Booking fake = new Booking(); fake.setId(555L);
        when(bookingService.createBooking(any(), any())).thenReturn(fake);

        String json = """
      {
        "departures":[{"id":1}],
        "returns":[{"id":2}]
      }
      """;

        mvc.perform(post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("555"));
    }

    @Test
    void createBooking_duplicate_respondsConflict() throws Exception {
        when(bookingService.createBooking(any(), any()))
                .thenThrow(new DuplicateBookingException("dup"));

        String json = """
      { "departures":[{"id":1}], "returns":[] }
      """;

        mvc.perform(post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }
}
