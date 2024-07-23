package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketSaveRequest;
import com.andersen.ticket_to_ride.dto.ticket.request.TicketFindRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponseNegative;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponsePositive;
import com.andersen.ticket_to_ride.entity.Ticket;
import com.andersen.ticket_to_ride.exception.ValidationException;
import com.andersen.ticket_to_ride.repository.TicketRepository;
import com.andersen.ticket_to_ride.strategy.priceCounter.PriceCountStrategy;
import com.andersen.ticket_to_ride.strategy.priceCounter.PriceCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;
    private final TicketRepository ticketRepository;
    private final PriceCountStrategy priceCountStrategy;

    /**
     * Create @ticketFindRequest for validating data and performing to @stationRouteService.
     *
     * @param departure the departure station
     * @param arrival   the arrival station
     * @param currency  the currency
     * @return the ticket DTO response
     * @throws ValidationException                                         if currency is invalid
     * @throws com.andersen.ticket_to_ride.exception.NoSuchEntityException if station not found.
     */
    public TicketDtoGetResponse findTicket(String departure, String arrival, String currency) {
        TicketFindRequest ticketFindRequest = new TicketFindRequest(departure, arrival, currency);
        Integer segments = stationRouteService
                .findShortestPath(ticketFindRequest.getDeparture(),
                        ticketFindRequest.getArrival());
        BigDecimal price = priceCountStrategy.countPrice(segments);
        return new TicketDtoGetResponse(segments, price, ticketFindRequest.getCurrency());
    }

    /**
     * Saves a new ticket.
     *
     * @param request the ticket DTO request
     * @return the ticket DTO positive or negative post response.
     * @throws com.andersen.ticket_to_ride.exception.ValidationException if at least one field is null.
     */
    public TicketDtoPostResponse saveTicket(TicketSaveRequest request) {
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

    private Ticket requestToTicket(TicketSaveRequest request) {
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
