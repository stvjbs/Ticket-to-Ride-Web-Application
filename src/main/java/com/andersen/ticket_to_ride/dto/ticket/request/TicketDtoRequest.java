package com.andersen.ticket_to_ride.dto.ticket.request;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO class for creating a new ticket POST request.
 */
@Data
public class TicketDtoRequest {
    private String departure;
    private String arrival;
    private Integer segments;
    private BigDecimal price;
    private Currency currency;
    private BigDecimal travellerAmount;
    private String traveller;
}
