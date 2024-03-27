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
        String chainName = "";
        try {
            chainName = rs.getString("chain_name");
        } catch (SQLException ignored) {
            
        }

        int hotelCount = 0;
        try {
            hotelCount = rs.getInt("hotel_count");
        } catch (SQLException ignored) {

        }
        
        return new HotelChain(chainName, hotelCount);
    }
}