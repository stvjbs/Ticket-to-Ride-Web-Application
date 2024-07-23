package com.andersen.ticket_to_ride.strategy.pathfinder;

import com.andersen.ticket_to_ride.dto.StationDto;

import java.util.List;

public interface PathfindingStrategy {

    Integer findPath(List<StationDto> stations, String start, String end);
}
