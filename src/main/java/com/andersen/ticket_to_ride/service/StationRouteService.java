package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.StationDto;
import com.andersen.ticket_to_ride.entity.Station;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.repository.StationRouteRepository;
import com.andersen.ticket_to_ride.strategy.pathfinder.PathfindingStrategy;
import com.andersen.ticket_to_ride.util.mapper.StationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationRouteService {

    private final StationRouteRepository stationRouteRepository;
    private final PathfindingStrategy pathfindingStrategy;

    public List<StationDto> findAllStations() {
        return StationDtoMapper.toDto(stationRouteRepository.findAll());
    }

    public Integer findShortestPath(String startStation, String endStation) {
        return pathfindingStrategy.findPath(findAllStations(), startStation, endStation);
    }

    Station findStationByName(String stationName) {
        return stationRouteRepository.findStationByCity(stationName)
                .orElseThrow(() -> new NoSuchEntityException("No station found with city: " + stationName));
    }
}
