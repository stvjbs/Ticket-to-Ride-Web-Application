package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.dto.TicketGetDto;
import com.andersen.Ticket_to_Ride_Web_Application.dto.TicketFindRequest;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.util.PriceCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;

    public TicketGetDto findTicket(TicketFindRequest ticketFindRequest) {
        Currency currency = ticketFindRequest.getCurrency();
        Integer segments = stationRouteService
                .findShortestPath(ticketFindRequest.getDeparture(),
                ticketFindRequest.getArrival());
        BigDecimal price = PriceCounter.countPrice(segments);
        return new TicketGetDto(segments, price, currency);
    }
}
