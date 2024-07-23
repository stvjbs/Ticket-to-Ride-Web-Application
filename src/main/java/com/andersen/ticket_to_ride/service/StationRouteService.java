package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.entity.Station;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.repository.StationRouteRepository;
import com.andersen.ticket_to_ride.strategy.pathfinder.PathfindingStrategy;
import com.andersen.ticket_to_ride.util.mapper.StationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling operations related to station routes.
 */
@Service
@RequiredArgsConstructor
public class StationRouteService {

    private final StationRouteRepository stationRouteRepository;
    private final PathfindingStrategy pathfindingStrategy;

    /**
     * Finds the shortest path between two stations.
     *
     * @param startStation the name of the start station.
     * @param endStation   the name of the end station.
     * @return the length of the shortest path.
     */
    public Integer findShortestPath(String startStation, String endStation) {
        return pathfindingStrategy.findPath(findAllStations(), startStation, endStation);
    }

    /**
     * Retrieves all stations and maps them to StationDto objects.
     *
     * @return a list of StationDto objects.
     */
    private List<StationDto> findAllStations() {
        return StationDtoMapper.toDto(stationRouteRepository.findAll());
    }

    /**
     * Finds a station by its name and maps it to a StationDto object.
     *
     * @param stationName the name of the station.
     * @return the StationDto object.
     * @throws NoSuchEntityException if no station is found with the given name.
     */
    StationDto findStationByName(String stationName) {
        Station station = stationRouteRepository.findStationByCity(stationName)
                .orElseThrow(() -> new NoSuchEntityException("No station found with city: " + stationName));
        return StationDtoMapper.toDto(station);
    }
}
