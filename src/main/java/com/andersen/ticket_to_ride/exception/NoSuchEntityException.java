package com.andersen.ticket_to_ride.exception;

import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@NoArgsConstructor
public class NoSuchEntityException extends NoSuchElementException {
    public NoSuchEntityException(String s) {
        super(s);
    }
}
