package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AddressDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ContactDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.RoomDao;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomControler {
    JdbcTemplate database;
    Gson serializer;
    RoomDao roomDao;

    public RoomControler(JdbcTemplate database, Gson serializer, RoomDao roomDao) {
        this.database = database;
        this.serializer = serializer;
        this.roomDao = roomDao;
    }

    @GetMapping("/info/{hotelId}")
    public String getRoomsInHotel(@PathVariable int hotelId){
        return serializer.toJson(roomDao.getRoomsByHotel(hotelId));
    }

}
