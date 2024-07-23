package com.andersen.ticket_to_ride.dto;

import com.andersen.ticket_to_ride.entity.Station;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RouteDto {
    private Long id;
    private StationDto start;
    private StationDto end;
    private Integer length;
}
