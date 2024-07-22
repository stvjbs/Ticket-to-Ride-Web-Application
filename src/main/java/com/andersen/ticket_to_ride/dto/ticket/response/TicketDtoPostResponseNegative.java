package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.entity.enums.ResultTicket;
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
