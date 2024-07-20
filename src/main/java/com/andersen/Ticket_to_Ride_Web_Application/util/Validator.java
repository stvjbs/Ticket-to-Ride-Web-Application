package com.andersen.Ticket_to_Ride_Web_Application.util;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.exception.ValidationException;

public class Validator {
    public static void validateCurrency(String currency) {
        if (currency == null) return;
        try {
            Currency curr = Currency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid currency");
        }
    }
}
