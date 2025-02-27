package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;

// to interact with database
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}