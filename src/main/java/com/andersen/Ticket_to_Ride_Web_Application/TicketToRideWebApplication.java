package com.andersen.Ticket_to_Ride_Web_Application;


import com.andersen.Ticket_to_Ride_Web_Application.service.StationRouteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TicketToRideWebApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TicketToRideWebApplication.class, args);
        StationRouteService service = applicationContext.getBean(StationRouteService.class);
        System.out.println(service.findShortestPath("Bristol", "London"));
        System.out.println(service.findShortestPath("Birmingham", "Bristol"));
        System.out.println(service.findShortestPath("Coventry", "Reading"));
    }

}
