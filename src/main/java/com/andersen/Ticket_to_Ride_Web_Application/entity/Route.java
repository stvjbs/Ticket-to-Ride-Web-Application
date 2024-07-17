package com.andersen.Ticket_to_Ride_Web_Application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Route {
    private final Station start;
    private final Station end;
    private final Integer length;

    public Route(@JsonProperty("start") Station start,
                 @JsonProperty("end") Station end,
                 @JsonProperty("length") Integer length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}
