package com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.ResultTicket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TicketDtoPostResponseNegative extends TicketDtoPostResponse {
    private ResultTicket resultTicket;
    private BigDecimal lackOf;

    public TicketDtoPostResponseNegative(BigDecimal lackOf, Currency currency) {
        this.lackOf = lackOf;
        super.setCurrency(currency);
        this.resultTicket = ResultTicket.FAILURE;
    }
}
