package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelChainMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class HotelChainDao {
    private static final String INSERT_HOTEL_CHAIN_SQL = "INSERT INTO hotel_chain (chain_name) VALUES (?)";
    private static final String SELECT_HOTEL_CHAIN_SQL = "SELECT * FROM hotel_chain WHERE chain_name = ?";
    private static final String SELECT_ALL_HOTEL_CHAIN_SQL = "SELECT * FROM hotel_chain";
    
    private final JdbcTemplate database;

    public HotelChainDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertHotelChain(HotelChain hotelChain) {
        database.update(INSERT_HOTEL_CHAIN_SQL, hotelChain.getChainName());
    }

    public List<HotelChain> getAllHotelChains() {
        HotelChainMapper mapper = new HotelChainMapper(database.getDataSource(), SELECT_ALL_HOTEL_CHAIN_SQL);
        return mapper.execute();
    }
    
    public HotelChain getHotelChainByChainName(String chainName) {
        HotelChainMapper mapper = new HotelChainMapper(database.getDataSource(), SELECT_HOTEL_CHAIN_SQL);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.findObject(chainName);
    }
}
