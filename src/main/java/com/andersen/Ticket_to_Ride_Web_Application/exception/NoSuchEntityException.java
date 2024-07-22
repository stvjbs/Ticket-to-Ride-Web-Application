package com.andersen.Ticket_to_Ride_Web_Application.exception;

import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;
@NoArgsConstructor
public class NoSuchEntityException extends NoSuchElementException {

    public NoSuchEntityException(String s) {
        super(s);
    }
}
