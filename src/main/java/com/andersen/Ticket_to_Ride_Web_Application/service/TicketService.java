package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.request.TicketDtoRequest;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponse;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponseNegative;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponsePositive;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketGetDtoResponse;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.request.TicketFindRequest;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.util.PriceCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;

    public TicketGetDtoResponse findTicket(String departure, String arrival, String currency) {
        TicketFindRequest ticketFindRequest = new TicketFindRequest(departure,arrival,currency);
        Integer segments = stationRouteService
                .findShortestPath(ticketFindRequest.getDeparture(),
                ticketFindRequest.getArrival());
        BigDecimal price = PriceCounter.countPrice(segments);
        return new TicketGetDtoResponse(segments, price, ticketFindRequest.getCurrency());
    }

    public TicketDtoResponse saveTicket(TicketDtoRequest request){
        BigDecimal travellerAmount = request.getTravellerAmount();
        BigDecimal price = travellerAmount.multiply(request.getPrice());
        if (travellerAmount.compareTo(price) < 0){
            TicketDtoResponseNegative ticketDtoResponseNegative =
                    new TicketDtoResponseNegative(price.subtract(travellerAmount));
            ticketDtoResponseNegative.setCurrency(request.getCurrency());
            return ticketDtoResponseNegative;
        }
        TicketDtoResponsePositive ticketDtoResponsePositive =
                new TicketDtoResponsePositive(travellerAmount.subtract(price));
        ticketDtoResponsePositive.setCurrency(request.getCurrency());
        return ticketDtoResponsePositive;
    }
}
