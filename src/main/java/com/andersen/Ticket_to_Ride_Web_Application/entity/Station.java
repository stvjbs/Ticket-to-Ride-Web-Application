package com.andersen.Ticket_to_Ride_Web_Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station implements Comparable<Station> {
    private final String city;
    private final List<Route> neighbours;
    private boolean visited;
    private Integer distance = Integer.MAX_VALUE;

    public Station(@JsonProperty("city") String city,
                   @JsonProperty("neighbours") List<Route> neighbours) {
        this.city = city;
        this.neighbours = neighbours;
    }

    @Override
    public int compareTo(Station o) {
        return this.distance.compareTo(o.distance);
    }
}