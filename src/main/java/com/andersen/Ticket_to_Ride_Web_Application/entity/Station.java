package com.andersen.Ticket_to_Ride_Web_Application.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Station {
    private String city;
    private Map<String, Integer> neighbourCities;
}
