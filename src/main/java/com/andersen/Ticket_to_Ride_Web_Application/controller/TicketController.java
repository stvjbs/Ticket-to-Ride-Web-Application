package com.andersen.Ticket_to_Ride_Web_Application.controller;

import com.andersen.Ticket_to_Ride_Web_Application.dto.TicketGetDto;
import com.andersen.Ticket_to_Ride_Web_Application.dto.TicketFindRequest;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<TicketGetDto> findTicket(@RequestParam String departure,
                                                   @RequestParam String arrival,
                                                   @RequestParam(required = false) String currency) {
        TicketFindRequest ticketFindRequest = new TicketFindRequest(departure,arrival,currency);
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.findTicket(ticketFindRequest));
    }

}
