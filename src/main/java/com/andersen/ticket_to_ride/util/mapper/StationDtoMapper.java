package com.andersen.ticket_to_ride.util.mapper;

import com.andersen.ticket_to_ride.dto.station_route.RouteDto;
import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.entity.Route;
import com.andersen.ticket_to_ride.entity.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for mapping between Station and StationDto objects.
 */
public class StationDtoMapper {

    private StationDtoMapper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts a Station entity to a StationDto without neighbours.
     *
     * @param station the Station entity.
     * @return the StationDto object.
     */
    public static StationDto toDto(Station station) {
        return StationDto.builder()
                .id(station.getId())
                .city(station.getCity())
                .build();
    }

    /**
     * Converts a Station entity to a StationDto with neighbours.
     *
     * @param station the Station entity.
     * @return the StationDto object with neighbours.
     */
    private static StationDto toDtoWithNeighbours(Station station) {
        StationDto stationDto = toDto(station);
        stationDto.setNeighbours(station.getNeighbours() != null ?
                station.getNeighbours().stream()
                        .map(StationDtoMapper::routeToDto)
                        .collect(Collectors.toSet())
                : Set.of());
        return stationDto;
    }

    /**
     * Converts a list of Station entities to a list of StationDto objects.
     *
     * @param stations the list of Station entities.
     * @return a list of StationDto objects.
     */
    public static List<StationDto> toDto(List<Station> stations) {
        List<StationDto> stationsDtoWithNeighbours = new ArrayList<>();
        stations.forEach(x-> stationsDtoWithNeighbours.add(toDtoWithNeighbours(x)));
        return stationsDtoWithNeighbours;
    }

    /**
     * Converts a Route entity to a RouteDto.
     *
     * @param route the Route entity.
     * @return the RouteDto object.
     */
    public static RouteDto routeToDto(Route route) {
        return RouteDto.builder()
                .id(route.getId())
                .start(toDto(route.getStart()))
                .end(toDto(route.getEnd()))
                .length(route.getLength())
                .build();
    }

    /**
     * Converts a StationDto to a Station entity.
     *
     * @param stationDto the StationDto object.
     * @return the Station entity.
     */
    public static Station toEntity(StationDto stationDto) {
        Station station = new Station();
        station.setId(stationDto.getId());
        station.setCity(stationDto.getCity());
        return station;
    }
}