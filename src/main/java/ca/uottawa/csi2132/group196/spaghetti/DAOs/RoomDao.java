package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Address;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.RoomQuery;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.RoomQueryResult;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomDao {
    private static final String INSERT_ROOM_SQL = "INSERT INTO room (hotel_id, room_number, price, view_type, capacity, extendable) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROOM_SQL = "SELECT * FROM room WHERE hotel_id = ? AND room_number = ?";

    // don't include room number in this query since room number makes the return value very long
    private static final String SELECT_ROOMS_BY_HOTEL_SQL = "SELECT DISTINCT hotel_id, price, view_type, capacity, extendable FROM room WHERE hotel_id = ?";
    private static final String SELECT_DISTINCT_ROOMS_SQL = "SELECT * FROM room WHERE hotel_id = ?";
    private static final String SELECT_ROOMS_BY_CHAIN = "SELECT * FROM room LEFT JOIN hotel on room.hotel_id = hotel.hotel_id WHERE hotel.owner = ?";
    private static final String SELECT_ROOMS_BY_CITY_SQL = "SELECT * FROM addresses, hotel_addresses, room WHERE addresses.address_id = hotel_addresses.address_id AND hotel_addresses.hotel_id = room.hotel_id AND addresses.city = ?";
    private static final String SELECT_ROOMS_BY_CAPACITY_SQL = "SELECT * FROM room WHERE capacity = ?";
    private static final String SELECT_ROOMS_BY_PRICE_SQL = "SELECT * FROM room WHERE price > ? AND price < ?";
    private static final String SELECT_ROOMS_BY_QUERY_SQL = "SELECT * FROM room WHERE price > ? AND price < ?";
    private static final String SELECT_FULL_QUERY_ROOMS_SQL = "SELECT * FROM giga_map WHERE (:price::decimal < 0.0 OR price = :price) AND (COALESCE(:chain_name, '') = '' OR owner = :chain_name) AND (COALESCE(:hotel_name, '') = '' OR hotel_name = :hotel_name) AND (COALESCE(:location, '') = '' OR (LOWER(street) LIKE LOWER(:location) OR LOWER(city) LIKE LOWER(:location) OR LOWER(province) LIKE LOWER(:location) OR LOWER(postal_code) LIKE LOWER(:location) OR LOWER(country) LIKE LOWER(:location))) AND (:rating < 0 OR rating = :rating) AND (:capacity < 0 OR capacity = :capacity)";
    private static final String UPDATE_ROOM_SQL = "UPDATE room SET hotel_id = ?, room_number = ?, price = ?, view_type = ?, capacity = ?, extendable = ? WHERE hotel_id = ? AND room_number = ?";
    private static final String DELETE_ROOM_SQL = "DELETE FROM room WHERE hotel_id = ? AND room_number = ?";

    private final JdbcTemplate database;
    private final NamedParameterJdbcTemplate namedDatabase;


    public RoomDao(DataSource dataSource) {
        this.database = new JdbcTemplate(dataSource);
        this.namedDatabase = new NamedParameterJdbcTemplate(dataSource);
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
        } catch (NumberFormatException ignored) {
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

    public List<Room> getRoomsByCapacity(int capacity) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOMS_BY_CAPACITY_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, capacity));
        return mapper.execute(capacity);
    }

    public List<Room> getRoomsByPrice(int min, int max) {
        FieldMapper<Room> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ROOMS_BY_PRICE_SQL, Room.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, min));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, max));
        return mapper.execute(min, max);
    }

    public List<RoomQueryResult> getRoomsByQuery(RoomQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("price", query.getPrice());
        params.put("chain_name", query.getChainName());
        params.put("hotel_name", query.getHotelName());
        params.put("location", query.getLocation());
        params.put("rating", query.getRating());
        params.put("capacity", query.getCapacity());

        return namedDatabase.query(SELECT_FULL_QUERY_ROOMS_SQL, params, (resultSet, rowNum) -> {
            RoomQueryResult result = new RoomQueryResult();
            Room room = new Room();
            room.setHotelId(resultSet.getInt("hotel_id"));
            room.setRoomNumber(resultSet.getInt("room_number"));
            room.setCapacity(resultSet.getInt("capacity"));
            room.setPrice(resultSet.getDouble("price"));
            room.setExtendable(resultSet.getBoolean("extendable"));
            room.setViewType(resultSet.getString("view_type"));

            result.setRoom(room);

            Address address = new Address();
            address.setStreet(resultSet.getString("street"));
            address.setCity(resultSet.getString("city"));
            address.setProvince(resultSet.getString("province"));
            address.setPostalCode(resultSet.getString("postal_code"));
            address.setCountry(resultSet.getString("country"));

            Hotel hotel = new Hotel();
            hotel.setHotelId(resultSet.getInt("hotel_id"));
            hotel.setHotelName(resultSet.getString("hotel_name"));
            hotel.setAddress(address);
            hotel.setOwner(resultSet.getString("owner"));
            hotel.setRating(resultSet.getInt("rating"));

            result.setHotel(hotel);

            return result;
        });
    }

    public void updateRoom(int originalHotelId, int originalRoomNumber, Room updatedRoom) {
        if (updatedRoom == null) return;
        Room originalRoom = getRoomByRoomNum(originalHotelId, originalRoomNumber);
        if (originalRoom == null) return;
        
        updatedRoom.fillFromInstance(originalRoom);
        database.update(UPDATE_ROOM_SQL, updatedRoom.getHotelId(), updatedRoom.getRoomNumber(), updatedRoom.getPrice(), updatedRoom.getViewType(), updatedRoom.getCapacity(), updatedRoom.isExtendable(), originalHotelId, originalRoomNumber);
    }

    public void deleteRoom(int hotelId, int roomNumber) {
        database.update(DELETE_ROOM_SQL, hotelId, roomNumber);
    }
}
