package com.andersen.ticket_to_ride.dto.ticket.request;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO class for creating a new ticket GET request.
 */
@Getter
@Setter
public class TicketGetRequest {
    private String departure;
    private String arrival;
    private Currency currency;

    /**
     * Constructs a TicketGetRequest with the specified parameters.
     *
     * @param departure the departure station
     * @param arrival   the arrival station
     * @param currency  the currency as a string
     */
    public TicketGetRequest(String departure, String arrival, String currency) {
        this.departure = departure;
        this.arrival = arrival;
        this.currency = validateAndMapCurrency(currency);
    }

    /**
     * Validates and maps the given currency string to the Currency enum.
     *
     * @param currency the currency as a string
     * @return the corresponding Currency enum value
     * @throws ValidationException if the currency is invalid
     */
    private Currency validateAndMapCurrency(String currency) {
        if (currency == null) return Currency.GBP;
        try {
            return Currency.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid currency");
        }
    }
}
