package com.andersen.ticket_to_ride.config;

import com.andersen.ticket_to_ride.strategy.pathfinder.DijkstraAlgorithm;
import com.andersen.ticket_to_ride.strategy.pathfinder.PathfindingStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathfindingConfig {
    @Bean
    @ConditionalOnProperty(name = "spring.application.pathfinding.algorithm", havingValue = "dijkstra")
    public PathfindingStrategy dijkstraAlgorithm() {
        return new DijkstraAlgorithm();
    }
}
