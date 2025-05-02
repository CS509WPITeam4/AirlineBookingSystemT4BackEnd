package com.cs509team4.AirlineReservation;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "booking" /*, cascade = CascadeType.ALL */)
    private List<BookingFlight> bookingFlights = new ArrayList<>();

    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(Long id, List<BookingFlight> bookingFlights) {
        this.id = id;
        this.bookingFlights = bookingFlights;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookingFlight> getBookingFlights() {
        return bookingFlights;
    }

    public void addBookingFlight(BookingFlight bf) {
        bookingFlights.add(bf);
        bf.setBooking(this); // maintain bidirectional link
    }

}

// Old insert statement
//INSERT INTO bookings (user_id, flight_number, depart_airport, arrive_airport, depart_datetime, arrive_datetime, status) VALUES
//(1, 'DL123', 'JFK', 'LAX', '2025-05-01 08:00:00', '2025-05-01 11:00:00', 'Confirmed'),
//        (2, 'UA456', 'ORD', 'SFO', '2025-05-02 09:30:00', '2025-05-02 12:15:00', 'Confirmed'),
//        (4, 'AA101', 'DFW', 'SEA', '2025-05-04 07:45:00', '2025-05-04 10:30:00', 'Confirmed'),
//        (5, 'BA202', 'MIA', 'BOS', '2025-05-05 17:00:00', '2025-05-05 20:00:00', 'Confirmed');