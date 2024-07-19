package com.andersen.Ticket_to_Ride_Web_Application.mapper;

import com.andersen.Ticket_to_Ride_Web_Application.dto.StationDto;
import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;

import java.util.List;
import java.util.stream.Collectors;

public class StationDtoMapper {
    public static List<StationDto> toDto(List<Station> stations) {
        return stations.stream()
                .map(StationDto::new)
                .collect(Collectors.toList());
    }
}
