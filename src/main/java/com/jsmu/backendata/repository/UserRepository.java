package com.jsmu.backendata.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private static final String QUERY = """
            SELECT username
            FROM "users"
            WHERE LOWER(username) LIKE :pattern
            ORDER BY username
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findUsernamesStartingWith(String normalizedPrefix) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("pattern", normalizedPrefix + "%");
        return jdbcTemplate.query(QUERY, parameters, (resultSet, rowNum) -> resultSet.getString("username"));
    }
}
