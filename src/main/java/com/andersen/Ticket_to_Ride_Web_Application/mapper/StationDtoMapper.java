package com.andersen.Ticket_to_Ride_Web_Application.mapper;

import com.andersen.Ticket_to_Ride_Web_Application.dto.StationDto;
import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;

import java.util.ArrayList;
import java.util.List;

public class StationDtoMapper {
    public static List<StationDto> toDto(List<Station> stations) {
        List<StationDto> stationDtos = new ArrayList<>();
        stations.forEach(x -> stationDtos.add(new StationDto(x)));
        return stationDtos;
    }
}
