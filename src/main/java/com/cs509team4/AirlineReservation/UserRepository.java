package com.cs509team4.AirlineReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// to interact with database
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}