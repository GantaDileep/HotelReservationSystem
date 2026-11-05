package com.demo.hotelreservation.exception;

public class RoomTypeNotFoundException extends RuntimeException {

    public RoomTypeNotFoundException(String message) {
        super(message);
    }
}