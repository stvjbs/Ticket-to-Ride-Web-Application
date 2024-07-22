package com.andersen.ticket_to_ride.dto.ticket.response;

import com.andersen.ticket_to_ride.entity.enums.Currency;
import lombok.Data;

@Data
public class TicketDtoPostResponse {
    private Currency currency;
}
