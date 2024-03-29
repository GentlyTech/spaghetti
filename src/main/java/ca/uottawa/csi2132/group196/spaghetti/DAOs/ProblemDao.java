package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProblemDao {
    private final JdbcTemplate database;

    public ProblemDao(JdbcTemplate database) {
        this.database = database;
    }
}
