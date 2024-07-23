package com.andersen.ticket_to_ride.dto.station_route;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RouteDto {
    private Long id;
    private StationDto start;
    private StationDto end;
    private Integer length;
}
