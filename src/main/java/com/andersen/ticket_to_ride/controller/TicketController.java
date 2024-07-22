package com.andersen.ticket_to_ride.controller;

import com.andersen.ticket_to_ride.dto.ticket.request.TicketDtoRequest;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoGetResponse;
import com.andersen.ticket_to_ride.dto.ticket.response.TicketDtoPostResponse;
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

    @GetMapping("count")
    public ResponseEntity<TicketDtoGetResponse> findTicket(@RequestParam String departure,
                                                           @RequestParam String arrival,
                                                           @RequestParam(required = false) String currency) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ticketService.findTicket(departure, arrival, currency));
    }

    @PostMapping("order")
    public ResponseEntity<TicketDtoPostResponse> saveTicket(@RequestBody TicketDtoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.saveTicket(request));
    }
}
