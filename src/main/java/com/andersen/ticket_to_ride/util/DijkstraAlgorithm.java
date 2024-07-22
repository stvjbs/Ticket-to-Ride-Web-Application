package com.andersen.ticket_to_ride.util;

import com.andersen.ticket_to_ride.dto.StationDto;
import com.andersen.ticket_to_ride.entity.Route;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    private DijkstraAlgorithm() {
        throw new IllegalStateException("Utility class");
    }

    public static Integer shortestPath(List<StationDto> stations,
                                       String start, String end) {
        StationDto startStation = findStationByName(stations, start);
        StationDto endStation = findStationByName(stations, end);
        PriorityQueue<StationDto> unvisitedQueue = new PriorityQueue<>();
        startStation.setDistance(0);
        unvisitedQueue.add(startStation);
        while (!unvisitedQueue.isEmpty()) {
            StationDto currStation = unvisitedQueue.poll();
            for (Route route : currStation.getNeighbours()) {
                StationDto neighbour = findStationByName(stations, route.getEnd().getCity());
                Integer routeLength = route.getLength();
                if (!neighbour.isVisited()
                        && currStation.getDistance() + routeLength < neighbour.getDistance()) {
                    neighbour.setDistance(currStation.getDistance() + routeLength);
                    if (unvisitedQueue.contains(neighbour)) {
                        unvisitedQueue.remove(neighbour);
                    }
                    unvisitedQueue.add(neighbour);
                }
            }
            currStation.setVisited(true);
        }
        return endStation.getDistance();
    }

    private static StationDto findStationByName(List<StationDto> stations, String city) {
        Optional<StationDto> stationOptional = stations.stream()
                .filter(x -> x.getStation().getCity().equals(city)).findFirst();
        if (stationOptional.isPresent()) {
            return stationOptional.get();
        }
        throw new NoSuchEntityException("No station found with city: " + city);
    }
}
