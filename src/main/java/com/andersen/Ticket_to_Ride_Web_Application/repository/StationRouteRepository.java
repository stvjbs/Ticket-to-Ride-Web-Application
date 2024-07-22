package com.andersen.Ticket_to_Ride_Web_Application.repository;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRouteRepository extends JpaRepository<Station, Long> {
    Optional<Station> findStationByCity(String city);
}
