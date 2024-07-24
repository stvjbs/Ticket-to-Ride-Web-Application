# Ticket-to-Ride Web Application

## Overview

This Java-based web application allows you to find the shortest path between stations using Dijkstra’s algorithm and includes features for calculating ticket prices and managing tickets.

## Features

- Find the shortest path between stations.
- Manage and validate tickets.
- Calculate ticket prices.
- Save ticket in database.

## Technologies

- Java 21
- Spring Boot
- Spring Data
- Spring Security
- JUnit 5, Mockito
- PostgreSQL
- Maven
- Docker

## Getting Started

### Requirements

- **Java 21**: Make sure Java 21 is installed.
- **PostgreSQL**: Install and run PostgreSQL. Create a database named `Ticket_to_ride_app`.
- **Docker**: Ensure Docker is installed if you plan to use Docker for setting up the PostgreSQL database.

### Installation

1. **Clone the Repository**

   git clone https://github.com/stvjbs/Ticket-to-Ride-Web-Application/develop

2. **Set Up PostgreSQL Database**

   1. *Build the Docker Image.* 
          Run the following commands in terminal:

         ``` docker build -t ticket-to-ride-db -f src/main/resources/docker/Dockerfile . ```
       
   2. *Run the Docker Container*
      
         ``` docker run --name ticket-to-ride-db -p 5432:5432 -d ticket-to-ride-db ```
   
   3. *Run SQL Script*

         ``` docker cp src/main/resources/insert_stations_graph.sql ticket-to-ride-db:/insert_stations_graph.sql ```
        
         ``` docker exec -it ticket-to-ride-db psql -U user -d Ticket_to_ride_app -f /insert_stations_graph.sql ```

3.**Build and Run**
   Build the project and run it:

   ``` mvn clean install ```
   ``` mvn spring-boot:run ```

5. **Access the application at http://localhost:8080.**


## API Endpoints

### Find Shortest Path

- **URL**: `ticket/findPath?departure={startStation}&arrival={endStation}&currency={currency}`
  -     or 'ticket/findPath?departure={city}&arrival={city}`
- **Method**: `GET`
- **Parameters**:
   - `startStation`: Name of the starting station
   - `endStation`: Name of the destination station
   - `currency`: Your currency
- **Description**: Finds the shortest path between two stations.

### Manage Tickets

- **URL**: `/ticket/order`
- **Method**: `POST`
- **Body**: JSON object with the following fields:
   - `departure`: Departure station name
   - `arrival`: Arrival station name
   - `price`: Ticket price
   - `currency`: Currency type
   - `segments`: Number of segments in the route
   - `traveller`: Traveller details
- **Description**: Saves a ticket and validates the price.

## Configuration

### Pathfinding Algorithm

Configure the pathfinding algorithm in `src/main/resources/application.yml`:

```yaml
spring:
  application:
    pathfinding:
      algorithm: dijkstra
```
### Price Calculation Strategy

Configure the price calculation strategy in `src/main/resources/application.yml`:

```yaml
spring:
  application:
    pricecounting:
      type: default