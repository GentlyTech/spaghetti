package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    JdbcTemplate database;
    Gson serializer;

    public HotelController(JdbcTemplate database) {
        this.database = database;
        this.serializer = CustomSerializer.getCustomSerializer();
    }
}
