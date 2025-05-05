package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingController;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.SelectedFlightsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerUnitTest {
    @Mock
    BookingService bookingService;
    @InjectMocks
    BookingController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_returnsCreatedId() {
        SelectedFlightsDTO dto = new SelectedFlightsDTO();
        dto.setDepartures(List.of());
        dto.setReturns(List.of());

        Booking b = new Booking();
        b.setId(314L);
        when(bookingService.createBooking(any(), any())).thenReturn(b);

        ResponseEntity<Long> resp = controller.createBooking(dto);
        assertEquals(201, resp.getStatusCodeValue());
        assertEquals(314L, resp.getBody());
    }
}
