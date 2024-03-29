package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import org.springframework.jdbc.core.JdbcTemplate;

public class AmenityDao {
    private final JdbcTemplate database;

    public AmenityDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertAmenitiesFromHotel(Hotel hotel) {

    }

    public void insertAmenitiesFromRoom(Room room) {

    }
}
