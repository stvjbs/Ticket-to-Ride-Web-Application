package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketPostRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponseNegative;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponsePositive;
import com.andersen.ticket_to_ride.entity.Station;
import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.exception.ValidationException;
import com.andersen.ticket_to_ride.repository.StationRouteRepository;
import com.andersen.ticket_to_ride.repository.TicketRepository;
import com.andersen.ticket_to_ride.strategy.pathfinder.PathfindingStrategy;
import com.andersen.ticket_to_ride.strategy.price_counter.PriceCountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    private final StationRouteRepository stationRouteRepository = mock(StationRouteRepository.class);
    private final PathfindingStrategy pathfindingStrategy = mock(PathfindingStrategy.class);
    private StationRouteService stationRouteService;
    private final TicketRepository ticketRepository = mock(TicketRepository.class);
    private final PriceCountStrategy priceCountStrategy = mock(PriceCountStrategy.class);
    private TicketService ticketService;
    private List<Station> stations;

    @BeforeEach
    public void setup() {
        stationRouteService = new StationRouteService(stationRouteRepository, pathfindingStrategy);
        ticketService = new TicketService(stationRouteService, ticketRepository, priceCountStrategy);
        Station start = new Station();
        start.setId(1L);
        start.setCity("StationA");
        Station end = new Station();
        end.setId(2L);
        end.setCity("StationB");

        stations = new ArrayList<>();
        stations.add(start);
        stations.add(end);
    }

    @Test
    void findTicket_ValidStationsAndCurrency_ReturnsTicketResponse() {
        String departure = "StationA";
        String arrival = "StationB";
        String currency = "GBP";
        when(stationRouteRepository.findAll()).thenReturn(stations);
        when(pathfindingStrategy.findPath(anyList(), eq("StationA"), eq("StationB"))).thenReturn(7);
        when(stationRouteService.findShortestPath(departure, arrival)).thenReturn(7);
        when(priceCountStrategy.countPrice(7)).thenReturn(BigDecimal.valueOf(25));

        TicketGetResponse response = ticketService.findTicket(departure, arrival, currency);

        assertNotNull(response);
        assertEquals(7, response.getSegments());
        assertEquals(BigDecimal.valueOf(25), response.getPrice());
        assertEquals(Currency.GBP, response.getCurrency());
    }

    @Test
    void findTicket_InvalidCurrency_ThrowsValidationException() {
        String departure = "ValidStationA";
        String arrival = "ValidStationB";
        String currency = "GBP12";
        Exception exception = assertThrows(ValidationException.class, () -> ticketService.findTicket(departure, arrival, currency));
        assertEquals("Invalid currency", exception.getMessage());
    }

    @Test
    void saveTicket_InsufficientFunds_ReturnsNegativeResponse() {
        TicketPostRequest request = new TicketPostRequest();
        request.setTravellerAmount(BigDecimal.valueOf(24));
        request.setPrice(BigDecimal.valueOf(25));
        request.setDeparture("StationA");
        request.setArrival("StationB");
        request.setSegments(7);
        request.setCurrency(Currency.GBP);
        request.setTraveller("john");
        TicketPostResponse response = ticketService.saveTicket(request);
        assertInstanceOf(TicketPostResponseNegative.class, response);
        assertEquals(BigDecimal.valueOf(1), ((TicketPostResponseNegative) response).getLackOf());
    }

    @Test
    void saveTicket_SufficientFunds_ReturnsPositiveResponse() {
        TicketPostRequest request = new TicketPostRequest();
        request.setTravellerAmount(BigDecimal.valueOf(26));
        request.setPrice(BigDecimal.valueOf(25));
        request.setDeparture("StationA");
        request.setArrival("StationB");
        request.setSegments(7);
        request.setCurrency(Currency.GBP);
        request.setTraveller("john");
        Station stationA = new Station();
        stationA.setId(1L);
        stationA.setCity("StationA");
        Station stationB = new Station();
        stationB.setId(2L);
        stationB.setCity("StationB");
        when(stationRouteRepository.findStationByCity("StationA")).thenReturn(Optional.of(stationA));
        when(stationRouteRepository.findStationByCity("StationB")).thenReturn(Optional.of(stationB));
        TicketPostResponse response = ticketService.saveTicket(request);

        assertInstanceOf(TicketPostResponsePositive.class, response);
        assertEquals(BigDecimal.valueOf(1), ((TicketPostResponsePositive) response).getChange());
    }

    @Test
    void saveTicket_NullEssentialFields_ThrowsValidationException() {
        TicketPostRequest request = new TicketPostRequest();
        request.setTravellerAmount(BigDecimal.valueOf(26));
        request.setPrice(BigDecimal.valueOf(25));
        request.setDeparture("StationA");
        request.setArrival("StationB");
        request.setSegments(7);
        request.setCurrency(Currency.GBP);
        Station stationA = new Station();
        stationA.setId(1L);
        stationA.setCity("StationA");
        Station stationB = new Station();
        stationB.setId(2L);
        stationB.setCity("StationB");
        when(stationRouteRepository.findStationByCity("StationA")).thenReturn(Optional.of(stationA));
        when(stationRouteRepository.findStationByCity("StationB")).thenReturn(Optional.of(stationB));
        assertThrows(ValidationException.class, () -> ticketService.saveTicket(request));
    }
}