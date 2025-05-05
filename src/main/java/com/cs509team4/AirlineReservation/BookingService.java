package com.cs509team4.AirlineReservation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final BookingFlightRepository bookingFlightRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingFlightRepository bookingFlightRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingFlightRepository = bookingFlightRepository;
    }

    @Transactional
    public Booking createBooking(List<FlightDTO> departures, List<FlightDTO> returns) {
        Booking booking = bookingRepository.save(new Booking());

        List<BookingFlight> bfs = new ArrayList<>();
        int seq = 1;
        for (FlightDTO dto : departures) {
            BookingFlight bf = new BookingFlight();
            bf.setBooking(booking);
            bf.setFlightId((long) dto.getId());
            bf.setLegType(LegType.DEPARTURE);
            bf.setSequence(seq++);
            bfs.add(bf);
        }

        if (returns != null) {
            seq = 1;
            for (FlightDTO dto : returns) {
                BookingFlight bf = new BookingFlight();
                bf.setBooking(booking);
                bf.setFlightId((long) dto.getId());
                bf.setLegType(LegType.RETURN);
                bf.setSequence(seq++);
                bfs.add(bf);
            }
        }

        bookingFlightRepository.saveAll(bfs);

        return booking;
    }

    public List<BookingDTO> getUserBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> result = new ArrayList<>();

        for (Booking booking : bookings) {
            List<BookingFlight> bookingFlights = bookingFlightRepository.findAllByBookingId(booking.getId());

            List<Long> departures = new ArrayList<>();
            List<Long> returns = new ArrayList<>();

            for (BookingFlight bf : bookingFlights) {
                if (bf.getLegType() == LegType.DEPARTURE) {
                    departures.add(bf.getFlightId());
                } else if (bf.getLegType() == LegType.RETURN) {
                    returns.add(bf.getFlightId());
                }
            }

            result.add(new BookingDTO(departures, returns));
        }

        return result;
    }
}
