package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketGetRequest;
import com.andersen.ticket_to_ride.dto.ticket.request.TicketPostRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponseNegative;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponsePositive;
import com.andersen.ticket_to_ride.entity.Station;
import com.andersen.ticket_to_ride.entity.Ticket;
import com.andersen.ticket_to_ride.exception.ValidationException;
import com.andersen.ticket_to_ride.repository.TicketRepository;
import com.andersen.ticket_to_ride.strategy.price_counter.PriceCountStrategy;
import com.andersen.ticket_to_ride.util.mapper.StationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service class for handling operations related to tickets.
 */
@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRouteService stationRouteService;
    private final TicketRepository ticketRepository;
    private final PriceCountStrategy priceCountStrategy;

    /**
     * Finds a ticket based on departure, arrival, and currency.
     *
     * @param departure the departure station.
     * @param arrival   the arrival station.
     * @param currency  the currency.
     * @return a TicketGetResponse object.
     */
    public TicketGetResponse findTicket(String departure, String arrival, String currency) {
        TicketGetRequest ticketGetRequest = new TicketGetRequest(departure, arrival, currency);
        Integer segments = stationRouteService
                .findShortestPath(ticketGetRequest.getDeparture(),
                        ticketGetRequest.getArrival());
        BigDecimal price = priceCountStrategy.countPrice(segments);
        return new TicketGetResponse(segments, price, ticketGetRequest.getCurrency());
    }

    /**
     * Saves a ticket based on the provided request.
     *
     * @param request the TicketPostRequest object.
     * @return a TicketPostResponse object.
     */
    public TicketPostResponse saveTicket(TicketPostRequest request) {
        BigDecimal travellerAmount = request.getTravellerAmount();
        BigDecimal price = request.getPrice();
        if (travellerAmount.compareTo(price) < 0) {
            return new TicketPostResponseNegative(price.subtract(travellerAmount), request.getCurrency());
        }
        Ticket ticket = requestToTicket(request);
        validateTicketNotNull(ticket);
        ticketRepository.save(ticket);
        return new TicketPostResponsePositive(travellerAmount.subtract(price), request.getCurrency());
    }

    /**
     * Converts a TicketPostRequest to a Ticket entity.
     *
     * @param request the TicketPostRequest object.
     * @return the Ticket entity.
     */
    private Ticket requestToTicket(TicketPostRequest request) {
        Station departure = StationDtoMapper.toEntity(stationRouteService.findStationByName(request.getDeparture()));
        Station arrival = StationDtoMapper.toEntity(stationRouteService.findStationByName(request.getArrival()));
        return Ticket.builder()
                .departure(departure)
                .arrival(arrival)
                .segments(request.getSegments())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .traveller(request.getTraveller())
                .build();
    }

    /**
     * Validates that none of the essential fields in the Ticket entity are null.
     *
     * @param ticket the Ticket entity.
     * @throws ValidationException if any essential field is null.
     */
    private void validateTicketNotNull(Ticket ticket) {
        boolean hasNull = ticket.getArrival() == null || ticket.getDeparture() == null
                || ticket.getSegments() == null || ticket.getPrice() == null
                || ticket.getCurrency() == null || ticket.getTraveller() == null;
        if (hasNull) {
            throw new ValidationException("One or more fields are null!");
        }
    }
}
