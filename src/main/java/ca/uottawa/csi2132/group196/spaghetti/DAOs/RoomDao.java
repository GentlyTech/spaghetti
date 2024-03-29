package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Problem;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Types.ViewType;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;

import java.sql.Types;
import java.util.List;

public class RoomDao {
    private final JdbcTemplate database;

    private int hotelId = -1;
    private int roomNumber = -1;
    private double price = 0.0;
    private double damageFee = 0.0;
    private boolean extendable = false;
    private String occupancyType = null;
    private String viewType = "boring";
    private List<Amenity> amenities = null;
    private List<Problem> problems = null;

    private static final String INSERT_ROOM_SQL = "INSERT INTO room (hotelId, roomNumber, price, damageFee, extendable, occupancyType, viewType, amenities, problems) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROOM_SQL = "SELECT * FROM room WHERE hotelId = ? AND roomNumber = ?";
    private static final String SELECT_ROOMS_BY_HOTEL_SQL = "SELECT * FROM room WHERE hotelId = ?";
    private static final String UPDATE_ROOMS_BY_HOTEL_SQL = "UPDATE room SET occ";


    public RoomDao(JdbcTemplate database){
        this.database = database;
    }

    public void insertRoom(Room room) {
        database.update(INSERT_ROOM_SQL, room.getHotelId(), room.getRoomNumber(), room.getPrice(), room.getDamageFee(), room.isExtendable(), room.getOccupancyType(), room.getViewType(), room.getAmenities(), room.getProblems());
    }

    public Room getRoomByRoomNum(int hotelId, int roomNumber) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOM_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "room_number"));
        return mapper.findObject(hotelId, roomNumber);
    }

    public List<Room> getRoomsByHotel(int hotelId) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOMS_BY_HOTEL_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotelId);
    }
}
