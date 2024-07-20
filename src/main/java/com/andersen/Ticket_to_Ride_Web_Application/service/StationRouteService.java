package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.dijkstraAlgorithm.DijkstraAlgorithm;
import com.andersen.Ticket_to_Ride_Web_Application.dto.StationDto;
import com.andersen.Ticket_to_Ride_Web_Application.util.mapper.StationDtoMapper;
import com.andersen.Ticket_to_Ride_Web_Application.repository.StationRouteRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class StationRouteService {

    private final StationRouteRepository stationRouteRepository;

    public List<StationDto> findAllStations() {
        return StationDtoMapper.toDto(stationRouteRepository.findAll());
    }

    public Integer findShortestPath(String startStation, String endStation) {
        return DijkstraAlgorithm.shortestPath(findAllStations(), startStation, endStation);
    }


}