package com.airline.reservation_api.controller;

import com.airline.reservation_api.model.Flight;
import com.airline.reservation_api.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Flight> getFlightByNumber(@RequestParam String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/route")
    public ResponseEntity<List<Flight>> getFlightsByRoute(
            @RequestParam String origin,
            @RequestParam String destination) {
        List<Flight> flights = flightRepository.findByRoute(origin, destination);
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight created = flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestBody Flight flight) {
        flight.setId(id);
        Flight updated = flightRepository.update(flight);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/seats")
    public ResponseEntity<Flight> updateSeats(
            @PathVariable Long id,
            @RequestParam int change) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            flight.setSeatsAvailable(flight.getSeatsAvailable() + change);
            Flight updated = flightRepository.update(flight);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        boolean deleted = flightRepository.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}