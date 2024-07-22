package com.andersen.Ticket_to_Ride_Web_Application.config;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Traveller;
import com.andersen.Ticket_to_Ride_Web_Application.exception.NoSuchEntityException;
import com.andersen.Ticket_to_Ride_Web_Application.repository.TravellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final TravellerRepository travellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Traveller traveller = travellerRepository.findByLogin(username).orElseThrow(NoSuchEntityException::new);
        return new User(traveller.getLogin(), traveller.getPassword(), List.of(
                new SimpleGrantedAuthority(traveller.getRole())
        ));
    }
}
