package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AmenityDao {
    private static final String INSERT_AMENITY_HOTEL_SQL = "INSERT INTO hotel_amenities (hotel_id, name, description) VALUES (?, ?, ?)";
    private static final String INSERT_AMENITY_ROOM_SQL = "INSERT INTO room_amenities (hotel_id, room_number, name, description) VALUES (?, ?, ?, ?, ?, ?)";

    private final JdbcTemplate database;

    public AmenityDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertAmenitiesFromHotel(Hotel hotel) {
        List<Amenity> amenities = hotel.getAmenities();
    }

    public void insertAmenitiesFromRoom(Room room) {

    }
}
