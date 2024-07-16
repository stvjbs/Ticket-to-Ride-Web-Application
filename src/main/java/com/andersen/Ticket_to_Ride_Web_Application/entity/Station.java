package com.andersen.Ticket_to_Ride_Web_Application.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Station {

    private final String city;
    private final Map<Station, Integer> neighbourCities;

}
