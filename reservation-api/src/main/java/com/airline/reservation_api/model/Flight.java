package com.airline.reservation_api.model;

import java.util.Objects;

public class Flight {
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private int seatsAvailable;

    public Flight() {}

    public Flight(String flightNumber, String origin, String destination, String departureTime, int seatsAvailable) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatsAvailable = seatsAvailable;
    }

    public Flight(Long id, String flightNumber, String origin, String destination, String departureTime, int seatsAvailable) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatsAvailable = seatsAvailable;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    @Override
    public String toString() {
        return "Flight{id=" + id + ", flightNumber='" + flightNumber + "', origin='" + origin +
                "', destination='" + destination + "', departureTime='" + departureTime +
                "', seatsAvailable=" + seatsAvailable + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Flight flight = (Flight) obj;
        return flightNumber.equals(flight.flightNumber) &&
                origin.equals(flight.origin) &&
                destination.equals(flight.destination) &&
                departureTime.equals(flight.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination, departureTime);
    }
}