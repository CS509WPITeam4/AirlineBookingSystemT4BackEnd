package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    BookingFlightRepository bookingFlightRepository;
    @InjectMocks
    BookingService bookingService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_onlyDepartures() {
        // Prepare two departures and no returns
        FlightDTO d1 = new FlightDTO(); d1.setId(101);
        FlightDTO d2 = new FlightDTO(); d2.setId(102);

        // Simulate saving initial booking
        Booking saved = new Booking();
        saved.setId(555L);
        when(bookingRepository.save(any())).thenReturn(saved);

        Booking result = bookingService.createBooking(List.of(d1, d2), null);

        // verify returned id
        assertEquals(555L, result.getId());

        // verify that two BookingFlight were saved with correct legType & sequences
        ArgumentCaptor<List<BookingFlight>> captor = ArgumentCaptor.forClass(List.class);
        verify(bookingFlightRepository).saveAll(captor.capture());

        List<BookingFlight> flights = captor.getValue();
        assertEquals(2, flights.size());

        BookingFlight bf1 = flights.get(0);
        assertEquals(101L, bf1.getFlightId());
        assertEquals(LegType.DEPARTURE, bf1.getLegType());
        assertEquals(1, bf1.getSequence());
        assertSame(saved, bf1.getBooking());

        BookingFlight bf2 = flights.get(1);
        assertEquals(102L, bf2.getFlightId());
        assertEquals(LegType.DEPARTURE, bf2.getLegType());
        assertEquals(2, bf2.getSequence());
    }

    @Test
    void createBooking_withReturns() {
        FlightDTO d1 = new FlightDTO(); d1.setId(1);
        FlightDTO r1 = new FlightDTO(); r1.setId(9);

        Booking saved = new Booking();
        saved.setId(777L);
        when(bookingRepository.save(any())).thenReturn(saved);

        Booking result = bookingService.createBooking(List.of(d1), List.of(r1));
        assertEquals(777L, result.getId());

        ArgumentCaptor<List<BookingFlight>> captor = ArgumentCaptor.forClass(List.class);
        verify(bookingFlightRepository).saveAll(captor.capture());
        List<BookingFlight> flights = captor.getValue();
        // one departure + one return
        assertEquals(2, flights.size());

        assertEquals(LegType.DEPARTURE, flights.get(0).getLegType());
        assertEquals(1, flights.get(0).getSequence());

        assertEquals(LegType.RETURN, flights.get(1).getLegType());
        assertEquals(1, flights.get(1).getSequence());
    }
}
