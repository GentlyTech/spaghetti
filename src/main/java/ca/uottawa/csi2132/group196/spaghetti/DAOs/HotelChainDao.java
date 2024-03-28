package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class HotelChainDao {
    private final JdbcTemplate database;
    private final SimpleJdbcInsert hotelChainInserter;
    
    public HotelChainDao(DataSource dataSource) {
        database = new JdbcTemplate(dataSource);
        hotelChainInserter = new SimpleJdbcInsert(dataSource);
    }
    
    public int insertHotelChain(HotelChain hotelChain) {
        Map<String, String> returnColumns = new HashMap<>();
        
    }
}
