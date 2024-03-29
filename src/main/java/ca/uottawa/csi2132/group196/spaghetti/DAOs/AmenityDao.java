package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.AmenityMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class AmenityDao {
    private static final String INSERT_AMENITY_HOTEL_SQL = "INSERT INTO hotel_amenities (hotel_id, name, description) VALUES (?, ?, ?)";
    private static final String INSERT_AMENITY_ROOM_SQL = "INSERT INTO room_amenities (hotel_id, room_number, name, description) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_AMENITY_HOTEL_SQL = "SELECT * FROM hotel_amenities WHERE hotel_id = ?";
    private static final String SELECT_AMENITY_ROOM_SQL = "SELECT * FROM room_amenities WHERE hotel_id = ? AND room_number = ?";

    private final JdbcTemplate database;

    public AmenityDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertAmenitiesFromHotel(Hotel hotel) {
        List<Amenity> amenities = hotel.getAmenities();
        for (Amenity amenity : amenities) {
            database.update(INSERT_AMENITY_HOTEL_SQL, hotel.getHotelId(), amenity.getName(), amenity.getDescription());
        }
    }

    public void insertAmenitiesFromRoom(Room room) {
        List<Amenity> amenities = room.getAmenities();
        for (Amenity amenity : amenities) {
            database.update(INSERT_AMENITY_ROOM_SQL, room.getHotelId(), room.getRoomNumber(), amenity.getName(), amenity.getDescription());
        }
    }

    public List<Amenity> getAmenitiesForHotel(int hotelId) {
        AmenityMapper mapper = new AmenityMapper(database.getDataSource(), SELECT_AMENITY_HOTEL_SQL);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotelId);
    }

    public List<Amenity> getAmenitiesForRoom(int hotelId, int roomNumber) {
        AmenityMapper mapper = new AmenityMapper(database.getDataSource(), SELECT_AMENITY_ROOM_SQL);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "room_number"));
        return mapper.execute(hotelId, roomNumber);
    }
}
