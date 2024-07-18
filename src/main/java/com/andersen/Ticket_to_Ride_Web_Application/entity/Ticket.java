package com.andersen.Ticket_to_Ride_Web_Application.entity;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.ResultTicket;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class Ticket {
    private long id;
    private Traveller traveller;
    private Station departure;
    private Station arrival;
    private Integer segments;
    private BigDecimal price;
    private Currency currency;
    private ResultTicket resultTicket;

}
