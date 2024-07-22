package com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.ResultTicket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TicketDtoResponsePositive extends TicketDtoResponse{
    private ResultTicket resultTicket;
    private BigDecimal change;

    public TicketDtoResponsePositive(BigDecimal change, Currency currency) {
        this.change = change;
        super.setCurrency(currency);
        this.resultTicket = ResultTicket.SUCCESS;
    }
}
