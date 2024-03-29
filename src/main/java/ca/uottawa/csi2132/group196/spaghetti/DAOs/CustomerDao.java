package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    private final JdbcTemplate database;

    public CustomerDao(JdbcTemplate database) {
        this.database = database;
    }
}
