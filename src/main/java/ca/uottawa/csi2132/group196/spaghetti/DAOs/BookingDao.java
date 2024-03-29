package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookingDao {
    private final JdbcTemplate database;

    public BookingDao(JdbcTemplate database) {
        this.database = database;
    }

}
