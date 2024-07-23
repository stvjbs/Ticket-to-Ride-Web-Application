package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO class for the response of a ticket controller GET request.
 */
@AllArgsConstructor
@Getter
@Setter
public class TicketDtoGetResponse {
    private Integer segments;
    private BigDecimal price;
    private Currency currency;
}
