package com.andersen.ticket_to_ride.repository;

import com.andersen.ticket_to_ride.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRouteRepository extends JpaRepository<Station, Long> {
    Optional<Station> findStationByCity(String city);
}
