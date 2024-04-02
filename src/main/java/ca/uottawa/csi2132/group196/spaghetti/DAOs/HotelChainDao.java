package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
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
    private static final String SELECT_ALL_HOTEL_CHAIN_NAMES_SQL = "SELECT DISTINCT chain_name FROM hotel_chain";
    private static final String DELETE_HOTEL_CHAIN_SQL = "DELETE FROM hotel_chain WHERE chain_name = ?";

    private final JdbcTemplate database;

    public HotelChainDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertHotelChain(HotelChain hotelChain) {
        database.update(INSERT_HOTEL_CHAIN_SQL, hotelChain.getChainName());
    }

    public List<HotelChain> getAllHotelChains() {
        FieldMapper<HotelChain> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ALL_HOTEL_CHAIN_SQL, HotelChain.class);
        return mapper.execute();
    }

    public List<String> getAllHotelChainNames() {
        return database.queryForList(SELECT_ALL_HOTEL_CHAIN_NAMES_SQL, String.class);
    }

    public HotelChain getHotelChainByChainName(String chainName) {
        FieldMapper<HotelChain> mapper = new FieldMapper<>(database.getDataSource(), SELECT_HOTEL_CHAIN_SQL, HotelChain.class);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.findObject(chainName);
    }
}
