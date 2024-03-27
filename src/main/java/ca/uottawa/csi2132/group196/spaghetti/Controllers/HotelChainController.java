package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;
import ca.uottawa.csi2132.group196.spaghetti.Serialization.HotelChain;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<HotelChain> results = database.query("SELECT * FROM hotel_chain", (result, rowNum) -> {
            String chainName = result.getString("chain_name");
            Object rawHotelCount = database.queryForObject("SELECT COUNT(*) FROM public.hotel WHERE owner = ?", Integer.class, chainName);
            int hotelCount;
            if (rawHotelCount == null) {
                hotelCount = 0;
            } else {
                hotelCount = (int) rawHotelCount;
            }
            return new HotelChain(chainName, hotelCount);
        });

        return serializer.toJson(results);
    }

    @GetMapping({"/info/{chain_name}", "/info/{chain_name}/"})
    public String getHotelChainInfo(@PathVariable String chain_name) {
        HotelChain finalResult = database.queryForObject("SELECT * FROM hotel_chain WHERE chain_name = ?", (result, rowNum) -> {
            String chainName = result.getString("chain_name");
            Object rawHotelCount = database.queryForObject("SELECT COUNT(*) FROM public.hotel WHERE owner = ?", Integer.class, chainName);
            int hotelCount;
            if (rawHotelCount == null) {
                hotelCount = 0;
            } else {
                hotelCount = (int) rawHotelCount;
            }
            return new HotelChain(chainName, hotelCount);
        }, chain_name);

        return serializer.toJson(finalResult);
    }
}
