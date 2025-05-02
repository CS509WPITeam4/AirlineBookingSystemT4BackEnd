package com.cs509team4.AirlineReservation.service;

import com.cs509team4.AirlineReservation.Booking;
import com.cs509team4.AirlineReservation.BookingDTO;
import com.cs509team4.AirlineReservation.BookingRepository;
import com.cs509team4.AirlineReservation.BookingService;
import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceDuplicateTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

//    @Test
//    void testCreateBooking_DuplicateThrows() {
//        BookingDTO dto = new BookingDTO();
//        dto.setUserId(1L);
//        dto.setFlightNumber("DL300");
//
//        when(bookingRepository.existsByUserIdAndFlightNumber(1L, "DL300"))
//                .thenReturn(true);
//
//        assertThrows(DuplicateBookingException.class,
//                () -> bookingService.createBooking(dto)
//        );
//
//        verify(bookingRepository, never()).save(any(Booking.class));
//    }

//    @Test
//    void testCreateBooking_SavesOnce() {
//        BookingDTO dto = new BookingDTO();
//        dto.setUserId(2L);
//        dto.setFlightNumber("WN400");
//
//        when(bookingRepository.existsByUserIdAndFlightNumber(2L, "WN400"))
//                .thenReturn(false);
//
//        Booking dummy = new Booking();
//        when(bookingRepository.save(any(Booking.class))).thenReturn(dummy);
//
//        bookingService.createBooking(dto);
//
//        verify(bookingRepository, times(1)).save(any(Booking.class));
//    }
}
