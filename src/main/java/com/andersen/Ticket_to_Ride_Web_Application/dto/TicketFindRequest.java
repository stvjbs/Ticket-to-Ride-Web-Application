package com.andersen.Ticket_to_Ride_Web_Application.dto;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.exception.ValidationException;
import lombok.Data;

@Data
public class TicketFindRequest {
    private String departure;
    private String arrival;
    private Currency currency;

    public TicketFindRequest(String departure, String arrival, String currency) {
        this.departure = departure;
        this.arrival = arrival;

        this.currency = validateAndMapCurrency(currency);
    }

    private Currency validateAndMapCurrency(String currency) {
        if (currency == null) return Currency.GBP;
        try {
            return Currency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid currency");
        }
    }
}
