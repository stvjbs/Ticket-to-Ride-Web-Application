package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.entity.enums.ResultTicket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TicketDtoPostResponsePositive extends TicketDtoPostResponse {
    private ResultTicket resultTicket;
    private BigDecimal change;

    public TicketDtoPostResponsePositive(BigDecimal change, Currency currency) {
        this.change = change;
        super.setCurrency(currency);
        this.resultTicket = ResultTicket.SUCCESS;
    }
}
