package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketDtoRequest;
import com.andersen.ticket_to_ride.dto.ticket.request.TicketFindRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponseNegative;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponsePositive;
import com.andersen.ticket_to_ride.entity.Ticket;
import com.andersen.ticket_to_ride.exception.ValidationException;
import com.andersen.ticket_to_ride.repository.TicketRepository;
import com.andersen.ticket_to_ride.util.PriceCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;
    private final TicketRepository ticketRepository;

    public TicketDtoGetResponse findTicket(String departure, String arrival, String currency) {
        TicketFindRequest ticketFindRequest = new TicketFindRequest(departure, arrival, currency);
        Integer segments = stationRouteService
                .findShortestPath(ticketFindRequest.getDeparture(),
                        ticketFindRequest.getArrival());
        BigDecimal price = PriceCounter.countPrice(segments);
        return new TicketDtoGetResponse(segments, price, ticketFindRequest.getCurrency());
    }

    public TicketDtoPostResponse saveTicket(TicketDtoRequest request) {
        BigDecimal travellerAmount = request.getTravellerAmount();
        BigDecimal price = request.getPrice();
        if (travellerAmount.compareTo(price) < 0) {
            return new TicketDtoPostResponseNegative(price.subtract(travellerAmount), request.getCurrency());
        }
        Ticket ticket = requestToTicket(request);
        validateTicketNotNull(ticket);
        ticketRepository.save(ticket);
        return new TicketDtoPostResponsePositive(travellerAmount.subtract(price), request.getCurrency());
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

    private void validateTicketNotNull(Ticket ticket) {
        boolean hasNull = ticket.getArrival() == null || ticket.getDeparture() == null
                || ticket.getSegments() == null || ticket.getPrice() == null
                || ticket.getCurrency() == null || ticket.getTraveller() == null;
        if (hasNull) {
            throw new ValidationException("One or more fields are null!");
        }
    }
}
