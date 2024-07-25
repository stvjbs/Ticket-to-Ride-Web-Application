package com.andersen.ticket_to_ride.util.mapper;

import com.andersen.ticket_to_ride.dto.station_route.RouteDto;
import com.andersen.ticket_to_ride.dto.station_route.StationDto;
import com.andersen.ticket_to_ride.entity.Route;
import com.andersen.ticket_to_ride.entity.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationDtoMapperTest {

    private Station station;
    private Route route;
    private StationDto stationDto;

    @BeforeEach
    void setUp() {
        station = new Station();
        station.setId(1L);
        station.setCity("CityA");

        Station neighbour = new Station();
        neighbour.setId(2L);
        neighbour.setCity("CityB");

        route = new Route();
        route.setId(1L);
        route.setStart(station);
        route.setEnd(neighbour);
        route.setLength(10);

        station.setNeighbours(Set.of(route));

        stationDto = StationDto.builder()
                .id(1L)
                .city("CityA")
                .build();
    }

    // Positive test cases

    @Test
    void toDto_ValidStation_ReturnsStationDto() {
        StationDto dto = StationDtoMapper.toDto(station);
        assertNotNull(dto);
        assertEquals(station.getId(), dto.getId());
        assertEquals(station.getCity(), dto.getCity());
    }

    @Test
    void toDtoWithNeighbours_ValidStation_ReturnsStationDtoWithNeighbours() {
        StationDto dto = StationDtoMapper.toDtoWithNeighbours(station);
        assertNotNull(dto);
        assertEquals(station.getId(), dto.getId());
        assertEquals(station.getCity(), dto.getCity());
        assertNotNull(dto.getNeighbours());
        assertEquals(1, dto.getNeighbours().size());

        RouteDto neighbourRouteDto = dto.getNeighbours().iterator().next();
        assertEquals(route.getId(), neighbourRouteDto.getId());
        assertEquals(route.getLength(), neighbourRouteDto.getLength());
        assertEquals(route.getStart().getId(), neighbourRouteDto.getStart().getId());
        assertEquals(route.getEnd().getId(), neighbourRouteDto.getEnd().getId());
    }

    @Test
    void toDto_ListOfStations_ReturnsListOfStationDtos() {
        List<Station> stations = List.of(station);
        List<StationDto> dtos = StationDtoMapper.toDto(stations);
        assertNotNull(dtos);
        assertEquals(1, dtos.size());

        StationDto dto = dtos.getFirst();
        assertEquals(station.getId(), dto.getId());
        assertEquals(station.getCity(), dto.getCity());
    }

    @Test
    void routeToDto_ValidRoute_ReturnsRouteDto() {
        RouteDto dto = StationDtoMapper.routeToDto(route);
        assertNotNull(dto);
        assertEquals(route.getId(), dto.getId());
        assertEquals(route.getLength(), dto.getLength());
        assertEquals(route.getStart().getId(), dto.getStart().getId());
        assertEquals(route.getEnd().getId(), dto.getEnd().getId());
    }

    @Test
    void toEntity_ValidStationDto_ReturnsStation() {
        Station entity = StationDtoMapper.toEntity(stationDto);
        assertNotNull(entity);
        assertEquals(stationDto.getId(), entity.getId());
        assertEquals(stationDto.getCity(), entity.getCity());
    }

    @Test
    void toDtoWithNeighbours_NullStation_ThrowsException() {
        assertThrows(NullPointerException.class, () -> StationDtoMapper.toDtoWithNeighbours(null));
    }
}