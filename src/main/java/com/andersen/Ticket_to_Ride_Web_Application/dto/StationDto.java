package com.andersen.Ticket_to_Ride_Web_Application.dto;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Route;
import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StationDto implements Comparable<StationDto> {
    private Station station;
    private boolean visited;
    private Integer distance;

    public StationDto(Station station) {
        this.station = station;
        distance = Integer.MAX_VALUE;
    }

    public Set<Route> getNeighbours(){
        return station.getNeighbours();
    }

    @Override
    public int compareTo(@NonNull StationDto o) {
        return Integer.compare(this.distance, o.distance);
    }
}
