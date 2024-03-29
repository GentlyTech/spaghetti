package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;

public class BookingDao {

    int room_number;
    int customer_id;
    int hotel_id;
    String booking_status;

    Date check_in_date;
    Date check_out_date;
    double damage_fee;

    private final JdbcTemplate database;

    public BookingDao(JdbcTemplate database) {
        this.database = database;
    }
}
