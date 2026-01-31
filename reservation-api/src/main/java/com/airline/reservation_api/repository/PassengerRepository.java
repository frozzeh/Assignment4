package com.airline.reservation_api.repository;

import com.airline.reservation_api.model.Passenger;
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
public class PassengerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Passenger> rowMapper = (rs, rowNum) -> {
        Passenger passenger = new Passenger();
        passenger.setId(rs.getLong("id"));
        passenger.setName(rs.getString("name"));
        passenger.setAge(rs.getInt("age"));
        return passenger;
    };

    public Passenger save(Passenger passenger) {
        String sql = "INSERT INTO passengers (name, age) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, passenger.getName());
            ps.setInt(2, passenger.getAge());
            return ps;
        }, keyHolder);

        passenger.setId(keyHolder.getKey().longValue());
        return passenger;
    }

    public List<Passenger> findAll() {
        String sql = "SELECT * FROM passengers";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Passenger findById(Long id) {
        String sql = "SELECT * FROM passengers WHERE id = ?";
        List<Passenger> passengers = jdbcTemplate.query(sql, rowMapper, id);
        return passengers.isEmpty() ? null : passengers.get(0);
    }

    public Passenger findByName(String name) {
        String sql = "SELECT * FROM passengers WHERE name = ?";
        List<Passenger> passengers = jdbcTemplate.query(sql, rowMapper, name);
        return passengers.isEmpty() ? null : passengers.get(0);
    }

    public Passenger update(Passenger passenger) {
        String sql = "UPDATE passengers SET name = ?, age = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, passenger.getName(), passenger.getAge(), passenger.getId());
        return rows > 0 ? passenger : null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM passengers WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}