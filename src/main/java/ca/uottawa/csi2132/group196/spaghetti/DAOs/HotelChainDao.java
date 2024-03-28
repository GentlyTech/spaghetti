package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class HotelChainDao {
    private final String INSERT_HOTEL_CHAIN_SQL = "INSERT INTO hotel_chain (chain_name) VALUES (?)";
    private final JdbcTemplate database;

    public HotelChainDao(DataSource dataSource) {
        database = new JdbcTemplate(dataSource);
    }

    public void insertHotelChain(HotelChain hotelChain) {
        database.update(INSERT_HOTEL_CHAIN_SQL, hotelChain.getChainName());
    }
}
