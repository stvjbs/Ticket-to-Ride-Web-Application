package com.andersen.Ticket_to_Ride_Web_Application.entity;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.ResultTicket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn
    @ManyToOne
    private Traveller traveller;
    @JoinColumn
    @ManyToOne
    private Station departure;
    @JoinColumn
    @ManyToOne
    private Station arrival;
    @Column(name = "segments")
    private Integer segments;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private ResultTicket resultTicket;
}
