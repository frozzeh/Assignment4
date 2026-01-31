package com.airline.reservation_api.repository;

import com.airline.reservation_api.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FlightRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Flight> rowMapper = (rs, rowNum) -> {
        Flight flight = new Flight();
        flight.setId(rs.getLong("id"));
        flight.setFlightNumber(rs.getString("flight_number"));
        flight.setOrigin(rs.getString("origin"));
        flight.setDestination(rs.getString("destination"));
        flight.setDepartureTime(rs.getString("departure_time"));
        flight.setSeatsAvailable(rs.getInt("seats_available"));
        return flight;
    };

    public Flight save(Flight flight) {
        String sql = "INSERT INTO flights (flight_number, origin, destination, departure_time, seats_available) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getOrigin());
            ps.setString(3, flight.getDestination());
            ps.setString(4, flight.getDepartureTime());
            ps.setInt(5, flight.getSeatsAvailable());
            return ps;
        }, keyHolder);

        flight.setId(keyHolder.getKey().longValue());
        return flight;
    }

    public List<Flight> findAll() {
        String sql = "SELECT * FROM flights";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Flight findById(Long id) {
        String sql = "SELECT * FROM flights WHERE id = ?";
        List<Flight> flights = jdbcTemplate.query(sql, rowMapper, id);
        return flights.isEmpty() ? null : flights.get(0);
    }

    public Flight findByFlightNumber(String flightNumber) {
        String sql = "SELECT * FROM flights WHERE flight_number = ?";
        List<Flight> flights = jdbcTemplate.query(sql, rowMapper, flightNumber);
        return flights.isEmpty() ? null : flights.get(0);
    }

    public List<Flight> findByRoute(String origin, String destination) {
        String sql = "SELECT * FROM flights WHERE origin = ? AND destination = ?";
        return jdbcTemplate.query(sql, rowMapper, origin, destination);
    }

    public Flight update(Flight flight) {
        String sql = "UPDATE flights SET flight_number = ?, origin = ?, destination = ?, departure_time = ?, seats_available = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql,
                flight.getFlightNumber(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDepartureTime(),
                flight.getSeatsAvailable(),
                flight.getId());
        return rows > 0 ? flight : null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}