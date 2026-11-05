package com.demo.hotelreservation.exception;

public class AmenityNotFoundException extends RuntimeException {

    public AmenityNotFoundException(String message) {
        super(message);
    }
}