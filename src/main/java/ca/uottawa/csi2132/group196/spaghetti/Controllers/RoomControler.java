package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.RoomDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.RoomQuery;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/info/byHotelId/{hotelId}")
    public String getRoomsInHotel(@PathVariable int hotelId) {
        return serializer.toJson(roomDao.getRoomsByHotel(hotelId));
    }

    @GetMapping("/info/byChain/{chainName}")
    public String getRoomsInHotelChain(@PathVariable String chainName) {
        return serializer.toJson(roomDao.getRoomsByChain(chainName));
    }

    @GetMapping("/info/byCity/{city}")
    public String getRoomsInCity(@PathVariable String city) {
        return serializer.toJson(roomDao.getRoomsByCity(city));
    }

    @GetMapping("/info/byCapacity/{capacity}")
    public String getRoomsByCapacity(@PathVariable int capacity) {
        return serializer.toJson(roomDao.getRoomsByCapacity(capacity));
    }

    @GetMapping("/info/byPrice/{minPrice}/{maxPrice}")
    public String getHotelsByPriceRange(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return serializer.toJson(roomDao.getRoomsByPrice(minPrice, maxPrice));
    }
    
    @PostMapping("/query")
    public String queryRooms(@RequestBody RoomQuery query) {
        return serializer.toJson(roomDao.getRoomsByQuery(query));
    }
}