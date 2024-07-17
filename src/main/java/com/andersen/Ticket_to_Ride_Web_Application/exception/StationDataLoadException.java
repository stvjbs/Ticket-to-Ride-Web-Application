package com.andersen.Ticket_to_Ride_Web_Application.exception;

public class StationDataLoadException extends RuntimeException {

    public StationDataLoadException(String message) {
        super(message);
    }

    public StationDataLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}