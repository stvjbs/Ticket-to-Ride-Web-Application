package com.andersen.ticket_to_ride.controller;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketPostRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketPostResponse;
import com.andersen.ticket_to_ride.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("findPath")
    public ResponseEntity<TicketGetResponse> findTicket(@RequestParam String departure,
                                                        @RequestParam String arrival,
                                                        @RequestParam(required = false) String currency) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ticketService.findTicket(departure, arrival, currency));
    }

    @PostMapping("order")
    public ResponseEntity<TicketPostResponse> saveTicket(@RequestBody TicketPostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.saveTicket(request));
    }
}
