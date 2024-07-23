package com.andersen.ticket_to_ride.util.mapper;

import com.andersen.ticket_to_ride.dto.StationDto;
import com.andersen.ticket_to_ride.entity.Station;

import java.util.List;

/**
 * Utility class for mapping Station entities to StationDto.
 * It helps to avoid multiple queries to DB, while algorithm is working.
 */
public class StationDtoMapper {

    private StationDtoMapper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts a list of Station entities to a list of StationDto.
     *
     * @param stations the list of Station entities to convert
     * @return a list of StationDto
     */
    public static List<StationDto> toDto(List<Station> stations) {
        return stations.stream().map(StationDto::new).toList();
    }
}
