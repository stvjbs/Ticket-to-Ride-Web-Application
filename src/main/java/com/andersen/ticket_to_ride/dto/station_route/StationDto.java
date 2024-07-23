package com.andersen.ticket_to_ride.dto.station_route;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class StationDto implements Comparable<StationDto> {

    private Long id;
    private String city;
    private Set<RouteDto> neighbours;
    private boolean visited;
    private Integer distance;

    @Override
    public int compareTo(StationDto o) {
        return Integer.compare(this.distance, o.distance);
    }
}
