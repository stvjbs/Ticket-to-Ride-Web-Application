package com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class TicketDtoGetResponse {
    private Integer segments;
    private BigDecimal price;
    private Currency currency;
}
