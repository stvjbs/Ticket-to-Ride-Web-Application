package com.andersen.ticket_to_ride.config.security;

import com.andersen.ticket_to_ride.entity.Traveller;
import com.andersen.ticket_to_ride.exception.NoSuchEntityException;
import com.andersen.ticket_to_ride.repository.TravellerRepository;
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
        Traveller traveller = travellerRepository
                .findByLogin(username).orElseThrow(NoSuchEntityException::new);
        return new User(traveller.getLogin(), traveller.getPassword(), List.of(
                new SimpleGrantedAuthority(traveller.getRole())
        ));
    }
}
