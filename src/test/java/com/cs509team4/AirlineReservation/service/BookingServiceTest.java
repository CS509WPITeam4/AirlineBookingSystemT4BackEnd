package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    BookingRepository bookingRepository;
    @Mock
    BookingFlightRepository bookingFlightRepository;
    @InjectMocks
    BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_savesBooking_andSavesAllFlights() {

        Booking savedBooking = new Booking();
        savedBooking.setId(99L);
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        FlightDTO d1 = new FlightDTO(); d1.setId(10);
        FlightDTO d2 = new FlightDTO(); d2.setId(20);
        FlightDTO r1 = new FlightDTO(); r1.setId(30);

        Booking result = bookingService.createBooking(
                List.of(d1, d2),
                List.of(r1)
        );

        assertSame(savedBooking, result);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<BookingFlight>> captor =
                ArgumentCaptor.forClass(List.class);

        verify(bookingFlightRepository).saveAll(captor.capture());
        List<BookingFlight> flights = captor.getValue();

        assertEquals(3, flights.size());

        BookingFlight bf0 = flights.get(0);
        assertEquals(10L, bf0.getFlightId());
        assertEquals(LegType.DEPARTURE, bf0.getLegType());
        assertEquals(1, bf0.getSequence());

        BookingFlight bf1 = flights.get(1);
        assertEquals(20L, bf1.getFlightId());
        assertEquals(LegType.DEPARTURE, bf1.getLegType());
        assertEquals(2, bf1.getSequence());

        BookingFlight bf2 = flights.get(2);
        assertEquals(30L, bf2.getFlightId());
        assertEquals(LegType.RETURN, bf2.getLegType());
        assertEquals(1, bf2.getSequence());
    }
}
