package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper extends MappingSqlQuery<Hotel> {
    public HotelMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();

        try {
            hotel.setHotelId(rs.getInt("hotel_id"));
        } catch (SQLException ignored) {

        }

        try {
            hotel.setRating(rs.getInt("rating"));
        } catch (SQLException ignored) {

        }

        try {
            hotel.setHotelName(rs.getString("hotel_name"));
        } catch (SQLException ignored) {

        }

        try {
            hotel.setOwner(rs.getString("owner"));
        } catch (SQLException ignored) {

        }
        
        return hotel;
    }
}
