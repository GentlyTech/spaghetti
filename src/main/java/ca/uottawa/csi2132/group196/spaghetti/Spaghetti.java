package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.*;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.*;
import ca.uottawa.csi2132.group196.spaghetti.Generators.AmenityGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Generators.RoomGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Utils.ResourceLoader;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
        ResourceLoader loader = new ResourceLoader(serializer);
        List<Amenity> hotelAmenities = List.of(loader.loadFromJsonFile("sampleData/HotelAmenities.json", Amenity[].class));
        List<Amenity> roomAmenities = List.of(loader.loadFromJsonFile("sampleData/RoomAmenities.json", Amenity[].class));

        loader.loadFromJsonFile("sampleData/HotelChains.json", HotelChain[].class, data -> {
            for (HotelChain hotelChain : data) {
                hotelChainDao.insertHotelChain(hotelChain);
                contactDao.insertContactsFromHotelChain(hotelChain);
                addressDao.insertAddressesFromHotelChain(hotelChain);
            }
        });

        loader.loadFromJsonFile("sampleData/Hotels.json", Hotel[].class, data -> {
            for (Hotel hotel : data) {
                if (hotel.getAmenities() == null || hotel.getAmenities().isEmpty()) {
                    hotel.setAmenities(new AmenityGenerator(hotelAmenities).generateAmenities());
                }

                int hotelId = hotelDao.insertHotel(hotel);
                hotel.setHotelId(hotelId);
                contactDao.insertContactsFromHotel(hotel);
                addressDao.insertAddressFromHotel(hotel);
                amenityDao.insertAmenitiesFromHotel(hotel);

                List<Room> rooms = new RoomGenerator(hotel, roomAmenities).generateRooms(100);
                roomDao.insertRooms(rooms);
            }
        });

        loader.loadFromJsonFile("sampleData/Employees.json", Employee[].class, data -> {
            for (Employee employee : data) {
                int employeeId = employeeDao.insertEmployee(employee);
                employee.setEmployeeId(employeeId);
                addressDao.insertAddressFromEmployee(employee);
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
