package com.andersen.ticket_to_ride.strategy.pathfinder;


import com.andersen.ticket_to_ride.dto.station_route.RouteDto;
import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.exception.StationNotConnectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DijkstraAlgorithmTest {

    private DijkstraAlgorithm dijkstraAlgorithm;
    private List<StationDto> stations;

    @BeforeEach
    void setUp() {
        dijkstraAlgorithm = new DijkstraAlgorithm();

        StationDto stationA = StationDto.builder().id(1L).city("StationA").build();
        StationDto stationB = StationDto.builder().id(2L).city("StationB").build();
        StationDto stationC = StationDto.builder().id(3L).city("StationC").build();

        RouteDto routeAB = RouteDto.builder().start(stationA).end(stationB).length(5).build();
        RouteDto routeBC = RouteDto.builder().start(stationB).end(stationC).length(10).build();
        RouteDto routeAC = RouteDto.builder().start(stationA).end(stationC).length(20).build();

        stationA.setNeighbours(Set.of(routeAB, routeAC));
        stationB.setNeighbours(Set.of(routeBC));
        stationC.setNeighbours(Set.of());
        stations = new ArrayList<>(Arrays.asList(stationA, stationB, stationC));
    }

    @Test
    void findPath_ValidStations_ReturnsShortestPath() {
        int shortestPath = dijkstraAlgorithm.findPath(stations, "StationA", "StationC");
        assertEquals(15, shortestPath);
    }

    @Test
    void findPath_InvalidStartStation_ThrowsException() {
        assertThrows(NoSuchEntityException.class, () -> dijkstraAlgorithm.findPath(stations, "InvalidStation", "StationC"));
    }

    @Test
    void findPath_InvalidEndStation_ThrowsException() {
        assertThrows(NoSuchEntityException.class, () -> dijkstraAlgorithm.findPath(stations, "StationA", "InvalidStation"));
    }

    @Test
    void findPath_NoPathAvailable_ThrowsStationNotConnectedException() {
        StationDto isolatedStation = StationDto.builder().id(4L).city("StationD").neighbours(Set.of()).build();
        stations.add(isolatedStation);
        Exception exception = assertThrows(StationNotConnectedException.class,
                () -> dijkstraAlgorithm.findPath(stations, "StationA", "StationD"));
        assertEquals("Station: " + isolatedStation.getCity() +
                " has no connection with StationA", exception.getMessage());
    }
}
