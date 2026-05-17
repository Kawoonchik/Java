package com.example.plantcare.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlantJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlantJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer countTotalPlants() {
        String sql = "SELECT COUNT(*) FROM plants";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}