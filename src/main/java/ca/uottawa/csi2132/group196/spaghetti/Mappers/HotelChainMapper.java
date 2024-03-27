package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelChainMapper extends MappingSqlQuery<HotelChain> {


    public HotelChainMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected HotelChain mapRow(ResultSet rs, int rowNum) {
        HotelChain hotelChain = new HotelChain();
        try {
            hotelChain.setChainName(rs.getString("chain_name"));
        } catch (SQLException ignored) {
            
        }
        
        try {
            hotelChain.setHotelCount(rs.getInt("hotel_count"));
        } catch (SQLException ignored) {

        }
        
        return hotelChain;
    }
}