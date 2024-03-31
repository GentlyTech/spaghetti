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
    private static final String SELECT_ROOM_SQL = "SELECT * FROM room WHERE hotel_id = ? AND room_number = ?";

    // don't include room number in this query since room number makes the return value very long
    private static final String SELECT_ROOMS_BY_HOTEL_SQL = "SELECT DISTINCT hotel_id, price, view_type, capacity, extendable FROM room WHERE hotel_id = ?";
    private static final String SELECT_DISTINCT_ROOMS_SQL = "SELECT * FROM room WHERE hotel_id = ?";

    private static final String SELECT_ROOMS_BY_CHAIN = "SELECT * FROM room LEFT JOIN hotel on room.hotel_id = hotel.hotel_id WHERE hotel.owner = ?";
    private static final String SELECT_ROOMS_BY_CITY_SQL = "SELECT * FROM addresses, hotel_addresses, room WHERE addresses.address_id = hotel_addresses.address_id AND hotel_addresses.hotel_id = room.hotel_id AND addresses.city = ?";
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

    public Room getRoomByRoomNum(String hotelId, String roomNumber) {
        try {
            return getRoomByRoomNum(Integer.parseInt(hotelId), Integer.parseInt(roomNumber));
        }
        catch (NumberFormatException ignored) {
            return null;
        }
    }

    public List<Room> getRoomsByHotel(int hotelId) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_DISTINCT_ROOMS_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotelId);
    }

    public List<Room> getDistinctRoomsByHotel(int hotelId) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_DISTINCT_ROOMS_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotelId);
    }

    public List<Room> getRoomsByChain(String chainName) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOMS_BY_CHAIN, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.VARCHAR, chainName));
        return mapper.execute(chainName);
    }

    public List<Room> getRoomsByCity(String city) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOMS_BY_CITY_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.VARCHAR, city));
        return mapper.execute(city);
    }
}
