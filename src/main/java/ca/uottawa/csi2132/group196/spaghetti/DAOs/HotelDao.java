package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class HotelDao {
    private final String INSERT_HOTEL_SQL = "INSERT INTO hotel (hotel_name, rating, owner) VALUES (?, ?, ?)";
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
    
    public Hotel getHotelById(int hotelId) {
        return null;
    }
}
