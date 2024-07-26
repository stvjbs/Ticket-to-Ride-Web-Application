package com.andersen.ticket_to_ride.util.mapper;

import com.andersen.ticket_to_ride.dto.station_route.RouteDto;
import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.entity.Route;
import com.andersen.ticket_to_ride.entity.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StationDtoMapperTest {


    private List<Station> stations;
    private Station stationA;
    private Route routeAB;

    @BeforeEach
    void setUp() {
        stationA = new Station();
        stationA.setId(1L);
        stationA.setCity("CityA");

        Station stationB = new Station();
        stationB.setId(2L);
        stationB.setCity("CityB");

        Station stationC = new Station();
        stationC.setId(3L);
        stationC.setCity("CityC");

        routeAB = new Route();
        routeAB.setId(1L);
        routeAB.setStart(stationA);
        routeAB.setEnd(stationB);
        routeAB.setLength(5);

        Route routeBC = new Route();
        routeBC.setId(2L);
        routeBC.setStart(stationB);
        routeBC.setEnd(stationC);
        routeBC.setLength(7);

        stationA.setNeighbours(Set.of(routeAB));
        stationB.setNeighbours(Set.of(routeBC));
        stationC.setNeighbours(Set.of());

        stations = new ArrayList<>();
        stations.add(stationA);
        stations.add(stationB);
        stations.add(stationC);
    }

    @Test
    void toDto_ValidStation_ReturnsStationDto() {
        StationDto stationDto = StationDtoMapper.toDto(stationA);

        assertNotNull(stationDto);
        assertEquals(stationA.getId(), stationDto.getId());
        assertEquals(stationA.getCity(), stationDto.getCity());
    }

    @Test
    void toDto_ListOfStations_ReturnsListOfStationDtos() {
        List<StationDto> stationDtos = StationDtoMapper.toDto(stations);

        assertNotNull(stationDtos);
        assertEquals(stations.size(), stationDtos.size());
        for (int i = 0; i < stations.size(); i++) {
            StationDto stationDto = stationDtos.get(i);
            Station station = stations.get(i);
            assertEquals(station.getId(), stationDto.getId());
            assertEquals(station.getCity(), stationDto.getCity());
            if (station.getNeighbours() != null && !station.getNeighbours().isEmpty()) {
                assertNotNull(stationDto.getNeighbours());
                assertFalse(stationDto.getNeighbours().isEmpty());
            } else {
                assertTrue(stationDto.getNeighbours().isEmpty());
            }
        }
    }

    @Test
    void routeToDto_ValidRoute_ReturnsRouteDto() {
        RouteDto routeDto = StationDtoMapper.routeToDto(routeAB);

        assertNotNull(routeDto);
        assertEquals(routeAB.getId(), routeDto.getId());
        assertEquals(routeAB.getLength(), routeDto.getLength());
        assertEquals(routeAB.getStart().getId(), routeDto.getStart().getId());
        assertEquals(routeAB.getEnd().getId(), routeDto.getEnd().getId());
    }

    @Test
    void toEntity_ValidStationDto_ReturnsStation() {
        StationDto stationDto = StationDto.builder()
                .id(stationA.getId())
                .city(stationA.getCity())
                .build();

        Station station = StationDtoMapper.toEntity(stationDto);

        assertNotNull(station);
        assertEquals(stationDto.getId(), station.getId());
        assertEquals(stationDto.getCity(), station.getCity());
    }
}