package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelChainMapper;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Types;
import java.util.List;

@RestController
@RequestMapping("/hotelChain")
public class HotelChainController {
    JdbcTemplate database;
    Gson serializer;

    public HotelChainController(JdbcTemplate database) {
        this.database = database;
        this.serializer = CustomSerializer.getCustomSerializer();
    }

    @GetMapping({"/info", "/info/"})
    public String getHotelChainInfoAll() {
        List<HotelChain> results = new HotelChainMapper(database.getDataSource(), "SELECT hotelChainInst.chain_name AS chain_name, COUNT(hotelInst.hotel_id) AS hotel_count FROM hotel_chain hotelChainInst FULL JOIN hotel hotelInst ON hotelChainInst.chain_name = hotelInst.owner GROUP BY hotelChainInst.chain_name;").execute();
        return serializer.toJson(results);
    }

    @GetMapping({"/info/{chain_name}", "/info/{chain_name}/"})
    public String getHotelChainInfo(@PathVariable String chain_name) {
        HotelChainMapper mapper = new HotelChainMapper(database.getDataSource(), "SELECT hotelChainInst.chain_name AS chain_name, COUNT(hotelInst.hotel_id) AS hotel_count FROM hotel_chain hotelChainInst LEFT JOIN hotel hotelInst ON hotelChainInst.chain_name = hotelInst.owner WHERE chain_name = ? GROUP BY hotelChainInst.chain_name;");
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        HotelChain result = mapper.findObject(chain_name);
        return serializer.toJson(result);
    }
}
