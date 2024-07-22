package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.request.TicketDtoRequest;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.request.TicketFindRequest;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponse;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponseNegative;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketDtoResponsePositive;
import com.andersen.Ticket_to_Ride_Web_Application.dto.ticket.response.TicketGetDtoResponse;
import com.andersen.Ticket_to_Ride_Web_Application.entity.Ticket;
import com.andersen.Ticket_to_Ride_Web_Application.repository.TicketRepository;
import com.andersen.Ticket_to_Ride_Web_Application.util.PriceCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;
    private final TicketRepository ticketRepository;

    public TicketGetDtoResponse findTicket(String departure, String arrival, String currency) {
        TicketFindRequest ticketFindRequest = new TicketFindRequest(departure, arrival, currency);
        Integer segments = stationRouteService
                .findShortestPath(ticketFindRequest.getDeparture(),
                        ticketFindRequest.getArrival());
        BigDecimal price = PriceCounter.countPrice(segments);
        return new TicketGetDtoResponse(segments, price, ticketFindRequest.getCurrency());
    }

    public TicketDtoResponse saveTicket(TicketDtoRequest request) {
        BigDecimal travellerAmount = request.getTravellerAmount();
        BigDecimal price = request.getPrice();
        if (travellerAmount.compareTo(price) < 0) {
            return new TicketDtoResponseNegative(price.subtract(travellerAmount), request.getCurrency());
        }
        TicketDtoResponsePositive ticketDtoResponsePositive =
                new TicketDtoResponsePositive(travellerAmount.subtract(price), request.getCurrency());
        Ticket ticket = requestToTicket(request);
        ticketRepository.save(ticket);
        return ticketDtoResponsePositive;
    }

    private Ticket requestToTicket(TicketDtoRequest request) {
        return Ticket.builder()
                .departure(stationRouteService.findStationByName(request.getDeparture()))
                .arrival(stationRouteService.findStationByName(request.getArrival()))
                .segments(request.getSegments())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .traveller(request.getTraveller())
                .build();
    }
}

