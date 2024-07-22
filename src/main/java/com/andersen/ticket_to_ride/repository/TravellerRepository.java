package com.andersen.ticket_to_ride.repository;

import com.andersen.ticket_to_ride.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravellerRepository extends JpaRepository<Traveller, Long> {
    Optional<Traveller> findByLogin(String login);
}
