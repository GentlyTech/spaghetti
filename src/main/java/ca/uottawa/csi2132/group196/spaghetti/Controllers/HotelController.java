package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.*;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Address;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    JdbcTemplate database;
    Gson serializer;
    AddressDao addressDao;
    AmenityDao amenityDao;
    BookingDao bookingDao;
    ContactDao contactDao;
    HotelDao hotelDao;
    RoomDao roomDao;

    public HotelController(JdbcTemplate database, Gson serializer, AddressDao addressDao, AmenityDao amenityDao, BookingDao bookingDao, ContactDao contactDao, HotelDao hotelDao, RoomDao roomDao) {
        this.database = database;
        this.serializer = serializer;
        this.addressDao = addressDao;
        this.amenityDao = amenityDao;
        this.bookingDao = bookingDao;
        this.contactDao = contactDao;
        this.hotelDao = hotelDao;
        this.roomDao = roomDao;
    }

    @GetMapping("/info/byChain/{chain_name}")
    public String getHotelInfoByHotelChain(@PathVariable String chain_name) {
        List<Hotel> results = hotelDao.getHotelsByChainName(chain_name);
        for (int i = 0; i < results.size(); i++) {
            Hotel hotel = results.get(i);
            hotel.setAddress(addressDao.getAddressForHotel(hotel.getHotelId()));
            hotel.setContacts(contactDao.getContactsForHotel(hotel.getHotelId()));
            hotel.setAmenities(amenityDao.getAmenitiesForHotel(hotel.getHotelId()));
            results.set(i, hotel);
        }
        if (results.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serializer.toJson(results);
    }

    @GetMapping("/info/byId/{hotel_id}")
    public String getHotelInfoByHotelId(@PathVariable String hotel_id) {
        Hotel result = hotelDao.getHotelById(hotel_id);

        if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        result.setAddress(addressDao.getAddressForHotel(result.getHotelId()));
        result.setContacts(contactDao.getContactsForHotel(result.getHotelId()));
        result.setAmenities(amenityDao.getAmenitiesForHotel(result.getHotelId()));
        return serializer.toJson(result);
    }

    @PostMapping("/info/byLocation")
    public String getHotelIdsByLocation(@RequestBody Address address) {
        List<Hotel> results = hotelDao.getHotelsByAddress(address);

        for (int i = 0; i < results.size(); i++) {
            Hotel hotel = results.get(i);
            hotel.setAddress(addressDao.getAddressForHotel(hotel.getHotelId()));
            hotel.setContacts(contactDao.getContactsForHotel(hotel.getHotelId()));
            hotel.setAmenities(amenityDao.getAmenitiesForHotel(hotel.getHotelId()));
            results.set(i, hotel);
        }

        return serializer.toJson(results);
    }

    @PostMapping("/checkRoom/{hotel_id}/{room_number}")
    public String checkRoomAvailability(@RequestBody Booking booking, @PathVariable String hotel_id, @PathVariable String room_number) {
        Room room = roomDao.getRoomByRoomNum(hotel_id, room_number);
        if (room == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serializer.toJson(!bookingDao.isBooked(booking.getCheckInDate(), booking.getCheckOutDate(), room));
    }
}
