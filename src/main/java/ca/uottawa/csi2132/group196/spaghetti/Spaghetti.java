package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.*;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Generators.RoomGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Utils.DatabasePopulator;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class Spaghetti {
    JdbcTemplate database;
    Gson serializer;

    AddressDao addressDao;
    AmenityDao amenityDao;
    BookingDao bookingDao;
    ContactDao contactDao;
    CustomerDao customerDao;
    EmployeeDao employeeDao;
    HotelChainDao hotelChainDao;
    HotelDao hotelDao;
    ProblemDao problemDao;
    RoleDao roleDao;
    RoomDao roomDao;
    UserDao userDao;

    public Spaghetti(JdbcTemplate database, Gson serializer, AddressDao addressDao, AmenityDao amenityDao, BookingDao bookingDao, ContactDao contactDao, CustomerDao customerDao, EmployeeDao employeeDao, HotelChainDao hotelChainDao, HotelDao hotelDao, ProblemDao problemDao, RoleDao roleDao, RoomDao roomDao, UserDao userDao) {
        this.database = database;
        this.serializer = serializer;
        this.addressDao = addressDao;
        this.amenityDao = amenityDao;
        this.bookingDao = bookingDao;
        this.contactDao = contactDao;
        this.customerDao = customerDao;
        this.employeeDao = employeeDao;
        this.hotelChainDao = hotelChainDao;
        this.hotelDao = hotelDao;
        this.problemDao = problemDao;
        this.roleDao = roleDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Spaghetti.class, args);
    }

    /**
     * Perform init actions here.
     */
    @PostConstruct
    public void onStartup() {
        DatabasePopulator populator = new DatabasePopulator(serializer);
        List<Amenity> hotelAmenities = List.of(populator.populateFromJsonFile("sampleData/HotelAmenities.json", Amenity[].class));
        List<Amenity> roomAmenities = List.of(populator.populateFromJsonFile("sampleData/RoomAmenities.json", Amenity[].class));

        populator.populateFromJsonFile("sampleData/HotelChains.json", HotelChain[].class, data -> {
            for (HotelChain hotelChain : data) {
                hotelChainDao.insertHotelChain(hotelChain);
                contactDao.insertContactsFromHotelChain(hotelChain);
                addressDao.insertAddressesFromHotelChain(hotelChain);
            }
        });

        populator.populateFromJsonFile("sampleData/Hotels.json", Hotel[].class, data -> {
            for (Hotel hotel : data) {
                int hotelId = hotelDao.insertHotel(hotel);
                hotel.setHotelId(hotelId);
                contactDao.insertContactsFromHotel(hotel);
                addressDao.insertAddressFromHotel(hotel);
                amenityDao.insertAmenitiesFromHotel(hotel);

                List<Room> rooms = new RoomGenerator(hotel, roomAmenities).generateRooms(100);
                for (Room room : rooms) {
                    roomDao.insertRoom(room);
                }
            }
        });

    }

    /**
     * Perform shutdown actions here.
     */
    @PreDestroy
    public void onShutdown() {

    }
}
