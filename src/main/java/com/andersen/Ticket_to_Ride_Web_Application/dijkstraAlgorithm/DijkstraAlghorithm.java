package com.andersen.Ticket_to_Ride_Web_Application.dijkstraAlgorithm;

import com.andersen.Ticket_to_Ride_Web_Application.entity.Route;
import com.andersen.Ticket_to_Ride_Web_Application.entity.Station;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.PriorityQueue;

@Component
public class DijkstraAlghorithm {
    private final PriorityQueue<Station> pq;
    private final List<Station> list;
    public DijkstraAlghorithm(List<Station> list) {
        this.list = list;
        this.pq = new PriorityQueue<>();
    }

    public Integer shortestPath(String start, String end) {
        Station startStation = list.stream().filter(x-> x.getCity().equals(start)).findFirst().get();
        Station endStation = list.stream().filter(x-> x.getCity().equals(end)).findFirst().get();
        startStation.setDistance(0);
        pq.add(startStation);
        while (!pq.isEmpty()) {
            Station currStation = pq.poll();
            for (Route route : currStation.getNeighbours()) {
                Station neighbour = findByCity(route.getEnd().getCity());
                Integer routeLength = route.getLength();

                if (!neighbour.isVisited()) {
                    if (currStation.getDistance() + routeLength < neighbour.getDistance()) {
                        neighbour.setDistance(currStation.getDistance() + routeLength);
                        if (pq.contains(neighbour)) {
                            pq.remove(neighbour);
                        }
                        pq.add(neighbour);
                    }
                }
            }
            currStation.setVisited(true);
        }
        return endStation.getDistance();
    }

    public Station findByCity(String city) {
        return list.stream().filter(x-> x.getCity().equals(city)).findFirst().get();
    }
}
