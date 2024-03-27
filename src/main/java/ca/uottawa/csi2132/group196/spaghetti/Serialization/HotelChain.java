package ca.uottawa.csi2132.group196.spaghetti.Serialization;

import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelChain extends MappingSqlQuery {

    private String chainName;
    private int hotelCount;

    public HotelChain() {

    }

    public HotelChain(String chainName, int hotelCount) {
        this.chainName = chainName;
        this.hotelCount = hotelCount;
    }

    public String getChainName() {
        return chainName;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    @Override
    protected HotelChain mapRow(ResultSet rs, int rowNum) throws SQLException {
        this.chainName = rs.getString("chain_name");
        this.hotelCount = rs.getInt("hotel_count");
        return this;
    }
}