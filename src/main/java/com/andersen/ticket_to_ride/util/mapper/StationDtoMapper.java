package com.andersen.ticket_to_ride.util.mapper;

import com.andersen.ticket_to_ride.dto.StationDto;
import com.andersen.ticket_to_ride.entity.Station;

import java.util.List;

public class StationDtoMapper {

    private StationDtoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<StationDto> toDto(List<Station> stations) {
        return stations.stream().map(StationDto::new).toList();
    }
}
