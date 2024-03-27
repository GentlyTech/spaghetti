package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelMapper;
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
@RequestMapping("/hotel")
public class HotelController {
    JdbcTemplate database;
    Gson serializer;

    public HotelController(JdbcTemplate database) {
        this.database = database;
        this.serializer = CustomSerializer.getCustomSerializer();
    }

    @GetMapping({"/info/byChain/{chain_name}", "/info/byChain/{chain_name}/"})
    public String getHotelInfoByHotelChain(@PathVariable String chain_name) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), "SELECT * FROM hotel WHERE owner = ?");
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        List<Hotel> results = mapper.execute(chain_name);
        return serializer.toJson(results);
    }

    @GetMapping({"/info/byId/{hotel_id}", "/info/byId/{hotel_id}/"})
    public String getHotelInfoByHotelId(@PathVariable String hotel_id) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), "SELECT * FROM hotel WHERE hotel_id = ?");
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        List<Hotel> results = mapper.execute(hotel_id);
        return serializer.toJson(results);
    }

    @GetMapping({"/info/byCity{city}", "/info/byCity{city}/"})
    public String getHotelByLocation(@PathVariable String city) {
        HotelMapper mapper = new HotelMapper(database.getDataSource(), "SELECT hotel_name, owner, rating, city, country, postal_code, street   FROM hotel JOIN hotel_addresses on hotel_addresses.address_id = hotel.hotel_id JOIN addresses on hotel_addresses.hotel_id = hotel.hotel_id WHERE addresses.city = ?");
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "city"));
        List<Hotel> results = mapper.execute(city);
        return serializer.toJson(results);
    }


    public String getHotelIdsByHotelName() {
        return null;
    }

}
