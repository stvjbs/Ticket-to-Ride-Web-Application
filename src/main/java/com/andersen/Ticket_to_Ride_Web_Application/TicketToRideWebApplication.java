package com.andersen.Ticket_to_Ride_Web_Application;


import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import com.andersen.Ticket_to_Ride_Web_Application.service.StationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class TicketToRideWebApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TicketToRideWebApplication.class, args);
		StationService service = applicationContext.getBean(StationService.class);
		System.out.println(service.getStations());
	}

}
