package com.andersen.ticket_to_ride.util;

import com.andersen.ticket_to_ride.dto.StationDto;
import com.andersen.ticket_to_ride.entity.Route;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Utility class for calculating the shortest path using Dijkstra's algorithm.
 * <p>
 * This class provides methods to find the shortest path between two stations
 * in a graph represented by a list of StationDto objects.
 * The main method, {@link #shortestPath(List,String,String)}, implements Dijkstra's algorithm
 * to find the shortest path.
 * </p>
 */
public class DijkstraAlgorithm {

    private DijkstraAlgorithm() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculates the shortest path between two stations using Dijkstra's algorithm.
     * <p>
     * This method finds the shortest path between the start station and the end station
     * in the given list of stations.
     * Each station has a list of neighboring stations with associated route lengths.
     * The method initializes the distance of the start station to zero(as if we are at this station) and uses
     * a priority queue to process each station in the order of their distance.
     * It updates the distance to each neighboring station if a shorter path is found.
     * </p>
     *
     * @param stations the list of StationDto objects, representing the graph
     * @param start the name of the start station
     * @param end the name of the end station
     * @return the shortest path distance from the start station to the end station
     * @throws com.andersen.ticket_to_ride.exception.NoSuchEntityException if either the start or end station is not found in the list
     */
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

    /**
     * Finds a station by its name.
     *
     * @param stations the list of stations
     * @param city the city name to search for
     * @return the StationDto object representing the station
     * @throws com.andersen.ticket_to_ride.exception.NoSuchEntityException if no station is found with the given city name
     */
    private static StationDto findStationByName(List<StationDto> stations, String city) {
        Optional<StationDto> stationOptional = stations.stream()
                .filter(x -> x.getStation().getCity().equals(city)).findFirst();
        if (stationOptional.isPresent()) {
            return stationOptional.get();
        }
        throw new NoSuchEntityException("No station found with city: " + city);
    }
}
