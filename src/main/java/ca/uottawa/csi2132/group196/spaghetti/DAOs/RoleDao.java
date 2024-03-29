package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;

public class RoleDao {
    private final JdbcTemplate database;

    public RoleDao(JdbcTemplate database) {
        this.database = database;
    }
}
