package com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.request;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

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
