package com.andersen.Ticket_to_Ride_Web_Application.exception;

public class ValidationException extends IllegalArgumentException{
    public ValidationException(String message) {
        super(message);
    }
}
