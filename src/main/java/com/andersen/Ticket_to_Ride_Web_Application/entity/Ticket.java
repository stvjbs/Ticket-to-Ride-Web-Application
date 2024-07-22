package com.andersen.Ticket_to_Ride_Web_Application.entity;

import com.andersen.Ticket_to_Ride_Web_Application.entity.enums.Currency;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "departure_id")
    @ManyToOne
    private Station departure;
    @JoinColumn(name = "arrival_id")
    @ManyToOne
    private Station arrival;
    @Column(name = "segments")
    private Integer segments;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "traveller")
    private String traveller;
}
