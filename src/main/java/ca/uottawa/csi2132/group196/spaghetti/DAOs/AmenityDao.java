package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;

public class AmenityDao {
    private final JdbcTemplate database;

    public AmenityDao(JdbcTemplate database) {
        this.database = database;
    }
}
