package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AddressDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ContactDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    JdbcTemplate database;
    Gson serializer;
    AddressDao addressDao;
    ContactDao contactDao;
    HotelDao hotelDao;

    public HotelController(JdbcTemplate database, Gson serializer, AddressDao addressDao, ContactDao contactDao, HotelDao hotelDao) {
        this.database = database;
        this.serializer = serializer;
        this.addressDao = addressDao;
        this.contactDao = contactDao;
        this.hotelDao = hotelDao;
    }

    @GetMapping({"/info/byChain/{chain_name}", "/info/byChain/{chain_name}/"})
    public String getHotelInfoByHotelChain(@PathVariable String chain_name) {
        List<Hotel> results = hotelDao.getHotelsByChainName(chain_name);
        for (int i = 0; i < results.size(); i++) {
            Hotel hotel = results.get(i);
            hotel.setAddresses(addressDao.getAddressesForHotel(hotel.getHotelId()));
            hotel.setContacts(contactDao.getContactsForHotel(hotel.getHotelId()));
            results.set(i, hotel);
        }
        if (results.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serializer.toJson(results);
    }

    @GetMapping({"/info/byId/{hotel_id}", "/info/byId/{hotel_id}/"})
    public String getHotelInfoByHotelId(@PathVariable String hotel_id) {
        Hotel result = hotelDao.getHotelById(hotel_id);
        
        if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        result.setAddresses(addressDao.getAddressesForHotel(result.getHotelId()));
        result.setContacts(contactDao.getContactsForHotel(result.getHotelId()));
        return serializer.toJson(result);
    }

    public String getHotelIdsByHotelName() {
        return null;
    }

    public String getHotelIdsByLocation() {
        return null;
    }
}
