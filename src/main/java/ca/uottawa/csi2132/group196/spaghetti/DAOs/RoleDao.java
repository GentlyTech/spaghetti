package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {
    private final JdbcTemplate database;

    public RoleDao(JdbcTemplate database) {
        this.database = database;
    }
}
