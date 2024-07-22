package com.andersen.ticket_to_ride.dto.ticket.request;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
            return Currency.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid currency");
        }
    }
}
