package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.entity.enums.ResultTicket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO class for the response of a successful ticket creation request.
 */
@Getter
@Setter
public class TicketDtoPostResponsePositive extends TicketDtoPostResponse {
    private ResultTicket resultTicket;
    private BigDecimal change;

    /**
     * Constructs a TicketDtoPostResponsePositive with the specified parameters.
     *
     * @param change   the amount of change
     * @param currency the currency of the transaction
     */
    public TicketDtoPostResponsePositive(BigDecimal change, Currency currency) {
        this.change = change;
        super.setCurrency(currency);
        this.resultTicket = ResultTicket.SUCCESS;
    }
}
