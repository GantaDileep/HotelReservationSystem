package com.demo.hotelreservation.exception;

public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String message) {
        super(message);
    }
}