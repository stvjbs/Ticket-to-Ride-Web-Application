package com.andersen.ticket_to_ride.exception;

public class StationNotConnectedException extends IllegalArgumentException {
    public StationNotConnectedException(String s) {
        super(s);
    }
}
