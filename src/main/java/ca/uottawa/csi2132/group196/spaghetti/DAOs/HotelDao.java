package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class HotelDao {
    private static final String INSERT_HOTEL_SQL = "INSERT INTO hotel (hotel_name, rating, owner) VALUES (?, ?, ?)";
    private static final String SELECT_HOTEL_BY_ID_SQL = "SELECT * FROM hotel WHERE hotel_id = ?";
    private static final String SELECT_HOTEL_BY_CHAIN_SQL = "SELECT * FROM hotel WHERE owner = ?";
    
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
        HotelMapper mapper = new HotelMapper(database.getDataSource(), SELECT_HOTEL_BY_CHAIN_SQL);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.execute(chainName);
    }

    public int getHotelCountByChainName(String chainName) {
        return getHotelsByChainName(chainName).size();
    }

    public Hotel getHotelById(int hotelId) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), SELECT_HOTEL_BY_ID_SQL);
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
}
