package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import lombok.Data;

/**
 * DTO class for the response of a ticket controller POST request with only one field @currency.
 * Instances of child-classes of this class are used as a return
 * for positive and negative cases at ticket controller.
 */
@Data
public class TicketDtoPostResponse {
    private Currency currency;
}
