package com.cs509team4.AirlineReservation.controller;

import com.cs509team4.AirlineReservation.DuplicateBookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateBookingException.class)
    public ResponseEntity<Void> handleDuplicate(DuplicateBookingException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
