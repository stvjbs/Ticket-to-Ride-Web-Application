package com.andersen.Ticket_to_Ride_Web_Application.repository;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravellerRepository extends JpaRepository<Traveller, Long> {
    Optional<Traveller> findByLogin(String login);
}
