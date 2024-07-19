package com.andersen.Ticket_to_Ride_Web_Application.dto;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import lombok.Data;

@Data
public class TicketFindRequest {
    private String departure;
    private String arrival;
    private Currency currency;
}
