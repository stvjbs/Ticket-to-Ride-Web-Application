package com.andersen.ticket_to_ride.service;

import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.entity.Station;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.repository.StationRouteRepository;
import com.andersen.ticket_to_ride.strategy.pathfinder.PathfindingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StationRouteServiceTest {
    @Mock
    private StationRouteRepository stationRouteRepository;

    @Mock
    private PathfindingStrategy pathfindingStrategy;

    @InjectMocks
    private StationRouteService stationRouteService;

    private List<Station> stations;

    @BeforeEach
    public void setup() {
        Station start = new Station();
        start.setId(1L);
        start.setCity("StationA");
        Station end = new Station();
        end.setId(2L);
        end.setCity("StationB");

        stations = new ArrayList<>();
        stations.add(start);
        stations.add(end);

    }

    @Test
    void findShortestPath_ValidStations_ReturnsShortestPath() {
        when(stationRouteRepository.findAll()).thenReturn(stations);
        when(pathfindingStrategy.findPath(anyList(), eq("StationA"), eq("StationB"))).thenReturn(5);

        Integer result = stationRouteService.findShortestPath("StationA", "StationB");

        assertNotNull(result);
        assertEquals(5, result);
    }

    @Test
    void findShortestPath_InvalidStations_ReturnsNull() {
        when(stationRouteRepository.findAll()).thenReturn(stations);
        when(pathfindingStrategy.findPath(anyList(), eq("StationX"), eq("StationY"))).thenReturn(null);

        Integer result = stationRouteService.findShortestPath("StationX", "StationY");

        assertNull(result);
    }

    @Test
    void findStationByName_ExistingStation_ReturnsStationDto() {
        Station station = new Station();
        station.setId(1L);
        station.setCity("StationA");

        when(stationRouteRepository.findStationByCity("StationA")).thenReturn(Optional.of(station));

        StationDto result = stationRouteService.findStationByName("StationA");

        assertNotNull(result);
        assertEquals("StationA", result.getCity());
    }

    @Test
    void findStationByName_NonExistingStation_ThrowsException() {
        when(stationRouteRepository.findStationByCity("StationA")).thenReturn(Optional.empty());

        NoSuchEntityException exception = assertThrows(NoSuchEntityException.class,
                () -> stationRouteService.findStationByName("StationA"));

        assertEquals("No station found with city: StationA", exception.getMessage());
    }
}
