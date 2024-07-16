package com.andersen.Ticket_to_Ride_Web_Application.config;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "stations")
public class StationsConfig {
    private List<Station> stations;
    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
