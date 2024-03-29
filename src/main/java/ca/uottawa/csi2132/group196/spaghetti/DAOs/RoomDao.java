package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDao {
    private static final String INSERT_ROOM_SQL = "INSERT INTO room (hotel_id, room_number, price, view_type, capacity, extendable) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROOM_SQL = "SELECT * FROM room WHERE hotelId = ? AND roomNumber = ?";
    private static final String SELECT_ROOMS_BY_HOTEL_SQL = "SELECT * FROM room WHERE hotelId = ?";
    private static final String UPDATE_ROOMS_BY_HOTEL_SQL = "";

    private final JdbcTemplate database;


    public RoomDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertRoom(Room room) {
        database.update(INSERT_ROOM_SQL, room.getHotelId(), room.getRoomNumber(), room.getPrice(), room.getViewType(), room.getCapacity(), room.isExtendable());
    }

    public void insertRooms(List<Room> rooms) {
        List<Object[]> batch = new ArrayList<>(rooms.size());
        for (Room room : rooms) {
            Object[] values = new Object[]{
                    room.getHotelId(), room.getRoomNumber(), room.getPrice(), room.getViewType(), room.getCapacity(), room.isExtendable()
            };
            batch.add(values);
        }
        database.batchUpdate(INSERT_ROOM_SQL, batch);
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
