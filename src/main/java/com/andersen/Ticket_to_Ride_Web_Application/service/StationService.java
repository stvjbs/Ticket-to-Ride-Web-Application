package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class StationService {

    private final List<Station> stations;

    public Station getStationByCity(String city) {
        return stations.stream()
                .filter(station -> station.getCity().equalsIgnoreCase(city))
                .findFirst()
                .orElse(null);
    }
}