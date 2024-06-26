package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Address;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class HotelDao {
    private static final String INSERT_HOTEL_SQL = "INSERT INTO hotel (hotel_name, rating, owner) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_HOTELS_SQL = "SELECT * FROM hotel";
    private static final String SELECT_HOTEL_BY_ID_SQL = "SELECT * FROM hotel WHERE hotel_id = ?";
    private static final String SELECT_HOTEL_BY_CHAIN_SQL = "SELECT * FROM hotel WHERE owner = ?";
    private static final String SELECT_HOTELS_BY_ADDRESS_SQL = "SELECT hotelInst.* FROM addresses addressInst LEFT JOIN hotel_addresses hotelRelInst ON addressInst.address_id = hotelRelInst.address_id LEFT JOIN hotel hotelInst ON hotelRelInst.hotel_id = hotelInst.hotel_id WHERE LOWER(addressInst.street) LIKE LOWER(?) OR LOWER(addressInst.city) LIKE LOWER(?) OR LOWER(addressInst.province) LIKE LOWER(?) OR LOWER(addressInst.postal_code) LIKE LOWER(?) OR LOWER(addressInst.country) LIKE LOWER(?)";
    private static final String COUNT_HOTEL_BY_CHAIN_SQL = "SELECT COUNT(*) FROM hotel WHERE owner = ?";
    private static final String SELECT_ALL_CITIES_WITH_HOTEL_SQL = "SELECT DISTINCT addresses.city FROM addresses, hotel_addresses  WHERE addresses.address_id = hotel_addresses.address_id";
    private static final String UPDATE_HOTEL_SQL = "UPDATE hotel SET hotel_name = ?, rating = ?, owner = ? WHERE hotel_id = ?";
    private static final String DELETE_HOTEL_SQL = "DELETE FROM hotel WHERE hotel_id = ?";

    private final JdbcTemplate database;

    public HotelDao(JdbcTemplate database) {
        this.database = database;
    }

    public int insertHotel(Hotel hotel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        database.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_HOTEL_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hotel.getHotelName());
            statement.setInt(2, hotel.getRating());
            statement.setString(3, hotel.getOwner());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) return -1;
        Number key = (Number) keys.get("hotel_id");
        if (key == null) return -1;
        return key.intValue();
    }

    public List<Hotel> getHotelsByChainName(String chainName) {
        FieldMapper<Hotel> mapper = new FieldMapper<>(database.getDataSource(), SELECT_HOTEL_BY_CHAIN_SQL, Hotel.class);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.execute(chainName);
    }

    public List<Hotel> getAllHotels() {
        FieldMapper<Hotel> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ALL_HOTELS_SQL, Hotel.class);
        return mapper.execute();
    }

    public List<Hotel> getHotelsByAddress(Address address) {
        FieldMapper<Hotel> mapper = new FieldMapper<>(database.getDataSource(), SELECT_HOTELS_BY_ADDRESS_SQL, Hotel.class);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "street"));
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "city"));
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "province"));
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "postal_code"));
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "country"));
        return mapper.execute(address.getStreet(), address.getCity(), address.getProvince(), address.getPostalCode(), address.getCountry());
    }

    public int getHotelCountByChainName(String chainName) {
        Integer result = database.queryForObject(COUNT_HOTEL_BY_CHAIN_SQL, Integer.class, chainName);
        if (result == null) return 0;
        return result;
    }

    public Hotel getHotelById(int hotelId) {
        FieldMapper<Hotel> mapper = new FieldMapper<>(database.getDataSource(), SELECT_HOTEL_BY_ID_SQL, Hotel.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.findObject(hotelId);
    }

    public Hotel getHotelById(String hotelId) {
        try {
            int parsedId = Integer.parseInt(hotelId);
            return getHotelById(parsedId);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    public List<String> getCities() throws SQLException {
        return database.queryForList(SELECT_ALL_CITIES_WITH_HOTEL_SQL, String.class);
    }

    public void updateHotel(Hotel updatedHotel) {
        if (updatedHotel == null) return;
        Hotel originalHotel = getHotelById(updatedHotel.getHotelId());
        if (originalHotel == null) return;
        
        updatedHotel.fillFromInstance(originalHotel);
        database.update(UPDATE_HOTEL_SQL, updatedHotel.getHotelName(), updatedHotel.getRating(), updatedHotel.getOwner(), updatedHotel.getHotelId());
    }
}
