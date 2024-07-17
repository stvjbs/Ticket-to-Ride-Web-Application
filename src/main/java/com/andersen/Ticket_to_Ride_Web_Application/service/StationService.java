package com.andersen.Ticket_to_Ride_Web_Application.service;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class StationService {

    private final List<Station> stations;

    public StationService(List<Station> stationList) {
        this.stations = stationList;
    }

    public Station getStationByCity(String city) {
        return stations.stream()
                .filter(station -> station.getCity().equalsIgnoreCase(city))
                .findFirst()
                .orElse(null);
    }
}