package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Problem;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Types.ViewType;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomMapper extends MappingSqlQuery<Room> {

    @Override
    protected Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();

        try {
            room.setHotelId(rs.getInt("hotel_id"));
        } catch (SQLException ignored){

        }

        try {
            room.setRoomNumber(rs.getInt("hotel_id"));
        } catch (SQLException ignored) {

        }

        try {
            room.setPrice(rs.getDouble("price"));
        } catch (SQLException ignored) {

        }

        try {
            room.setDamageFee(rs.getDouble("damage_fee"));
        } catch (SQLException ignored) {

        }

        try {
            room.setExtendable(rs.getBoolean("extendable"));
        } catch (SQLException ignored) {

        }

        try {
            room.setOccupancyType(rs.getString("occupancy_type"));
        } catch (SQLException ignored) {

        }

        try {
            room.setViewType(ViewType.getView(rs.getInt("view_type")));
        } catch (SQLException ignored) {

        }

        return room;
    }
}
