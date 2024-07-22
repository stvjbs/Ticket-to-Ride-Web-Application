package com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import lombok.Data;

@Data
public class TicketDtoPostResponse {
    private Currency currency;
}
