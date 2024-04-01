package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AmenityDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ProblemDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.RoomDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.RoomQuery;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.RoomQueryResult;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomControler {
    JdbcTemplate database;
    Gson serializer;

    AmenityDao amenityDao;
    ProblemDao problemDao;
    RoomDao roomDao;

    public RoomControler(JdbcTemplate database, Gson serializer, AmenityDao amenityDao, ProblemDao problemDao, RoomDao roomDao) {
        this.database = database;
        this.serializer = serializer;
        this.amenityDao = amenityDao;
        this.problemDao = problemDao;
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
        List<RoomQueryResult> results = roomDao.getRoomsByQuery(query);
        
        for (int i = 0; i < results.size(); i++) {
            RoomQueryResult result = results.get(i);
            Room room = result.getRoom();
            room.setAmenities(amenityDao.getAmenitiesForRoom(room.getHotelId(), room.getRoomNumber()));
            room.setProblems(problemDao.getProblemsWithRoom(room.getHotelId(), room.getRoomNumber()));
            result.setRoom(room);
            results.set(i, result);
        }
        
        return serializer.toJson(results);
    }
}
