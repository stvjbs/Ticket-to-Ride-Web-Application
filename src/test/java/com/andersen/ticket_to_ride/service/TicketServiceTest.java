package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.dto.ticket.request.TicketPostRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponseNegative;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponsePositive;
import com.andersen.ticket_to_ride.entity.enums.Currency;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.exception.ValidationException;
import com.andersen.ticket_to_ride.repository.TicketRepository;
import com.andersen.ticket_to_ride.strategy.price_counter.PriceCountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private StationRouteService stationRouteService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PriceCountStrategy priceCountStrategy;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findTicket_ValidStationsAndCurrency_ReturnsTicketResponse() {
        String departure = "StationA";
        String arrival = "StationB";
        String currency = "GBP";

        when(stationRouteService.findShortestPath(departure, arrival)).thenReturn(5);
        when(priceCountStrategy.countPrice(5)).thenReturn(BigDecimal.valueOf(100));

        TicketGetResponse response = ticketService.findTicket(departure, arrival, currency);

        assertNotNull(response);
        assertEquals(5, response.getSegments());
        assertEquals(BigDecimal.valueOf(100), response.getPrice());
        assertEquals(Currency.GBP, response.getCurrency());
    }

    @Test
    void findTicket_InvalidStations_ThrowsNoSuchEntityException() {
        String departure = "InvalidStationA";
        String arrival = "InvalidStationB";
        String currency = "GBP";

        when(stationRouteService.findShortestPath(departure, arrival))
                .thenThrow(new NoSuchEntityException("No station found with name: " + departure));
        when(priceCountStrategy.countPrice(5)).thenReturn(BigDecimal.valueOf(100));

        TicketGetResponse response = ticketService.findTicket(departure, arrival, currency);

        assertNotNull(response);
        assertNull(response.getPrice());
        assertEquals(Currency.GBP, response.getCurrency());
    }

        @Test
        void saveTicket_InsufficientFunds_ReturnsNegativeResponse () {
            TicketPostRequest request = new TicketPostRequest();
            request.setTravellerAmount(BigDecimal.valueOf(50));
            request.setPrice(BigDecimal.valueOf(100));
            request.setDeparture("StationA");
            request.setArrival("StationB");
            request.setSegments(5);
            request.setCurrency(Currency.GBP);
            request.setTraveller("john");

            TicketPostResponse response = ticketService.saveTicket(request);

            assertTrue(response instanceof TicketPostResponseNegative);
            assertEquals(BigDecimal.valueOf(50), ((TicketPostResponseNegative) response).getLackOf());
        }

        @Test
        void saveTicket_SufficientFunds_ReturnsPositiveResponse () {
            TicketPostRequest request = new TicketPostRequest();
            request.setTravellerAmount(BigDecimal.valueOf(150));
            request.setPrice(BigDecimal.valueOf(100));
            request.setDeparture("StationA");
            request.setArrival("StationB");
            request.setSegments(5);
            request.setCurrency(Currency.GBP);
            request.setTraveller("john");

            StationDto stationA = StationDto.builder().build();
            StationDto stationB = StationDto.builder().build();

            when(stationRouteService.findStationByName("StationA")).thenReturn(stationA);
            when(stationRouteService.findStationByName("StationB")).thenReturn(stationB);

            TicketPostResponse response = ticketService.saveTicket(request);

            assertInstanceOf(TicketPostResponsePositive.class, response);
            assertEquals(BigDecimal.valueOf(50), ((TicketPostResponsePositive) response).getChange());
        }

        @Test
        void saveTicket_NullEssentialFields_ThrowsValidationException () {
            TicketPostRequest request = new TicketPostRequest();
            request.setTravellerAmount(BigDecimal.valueOf(150));
            request.setPrice(BigDecimal.valueOf(100));
            request.setDeparture("StationA");
            request.setArrival("StationB");
            request.setSegments(5);
            request.setCurrency(Currency.GBP);
            request.setTraveller("john");

            when(stationRouteService.findStationByName("StationA")).thenReturn(null);

            assertThrows(ValidationException.class, () -> {
                ticketService.saveTicket(request);
            });
        }
    }