package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Types;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    JdbcTemplate database;
    Gson serializer;

    public HotelController(JdbcTemplate database, Gson serializer) {
        this.database = database;
        this.serializer = serializer;
    }

    @GetMapping({"/info/byChain/{chain_name}", "/info/byChain/{chain_name}/"})
    public String getHotelInfoByHotelChain(@PathVariable String chain_name) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), "SELECT * FROM hotel WHERE owner = ?");
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        List<Hotel> results = mapper.execute(chain_name);
        if (results.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serializer.toJson(results);
    }

    @GetMapping({"/info/byId/{hotel_id}", "/info/byId/{hotel_id}/"})
    public String getHotelInfoByHotelId(@PathVariable String hotel_id) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), "SELECT * FROM hotel WHERE hotel_id = ?");
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        List<Hotel> results = mapper.execute(hotel_id);
        if (results.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serializer.toJson(results);
    }

    public String getHotelIdsByHotelName() {
        return null;
    }

    public String getHotelIdsByLocation() {
        return null;
    }
}
