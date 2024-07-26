package com.andersen.ticket_to_ride;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TicketToRideWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketToRideWebApplication.class, args);
    }
}
