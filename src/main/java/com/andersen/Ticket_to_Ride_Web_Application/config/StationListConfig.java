package com.andersen.Ticket_to_Ride_Web_Application.config;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import com.andersen.Ticket_to_Ride_Web_Application.exception.StationDataLoadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StationListConfig {
    private final ResourceLoader resourceLoader;

    @Value("${spring.application.config.sourcepathForStations}")
    private String classPath;

    @Bean
    public List<Station> stationList() {
        try {
            Resource resource = resourceLoader.getResource(classPath);
            InputStream inputStream = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Station>> typeReference = new TypeReference<>() {};
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new StationDataLoadException("Failed to load station data from JSON file", e);
        }
    }
}
