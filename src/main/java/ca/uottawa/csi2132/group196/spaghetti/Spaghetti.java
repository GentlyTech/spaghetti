package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.*;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.*;
import ca.uottawa.csi2132.group196.spaghetti.Generators.AmenityGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Generators.BookingGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Generators.RoomGenerator;
import ca.uottawa.csi2132.group196.spaghetti.Utils.ResourceLoader;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.LinkedList;
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
        List<Customer> customers = Arrays.asList(loader.loadFromJsonFile("sampleData/Customers.json", Customer[].class));
        List<Room> rooms = new LinkedList<>();

        loader.loadFromJsonFile("sampleData/HotelChains.json", HotelChain[].class, hotelChains -> {
            for (HotelChain hotelChain : hotelChains) {
                hotelChainDao.insertHotelChain(hotelChain);
                contactDao.insertContactsFromHotelChain(hotelChain);
                addressDao.insertAddressesFromHotelChain(hotelChain);
            }
        });

        loader.loadFromJsonFile("sampleData/Hotels.json", Hotel[].class, hotels -> {
            for (Hotel hotel : hotels) {
                if (hotel.getAmenities() == null || hotel.getAmenities().isEmpty()) {
                    hotel.setAmenities(new AmenityGenerator(hotelAmenities).generateAmenities());
                }

                int hotelId = hotelDao.insertHotel(hotel);
                hotel.setHotelId(hotelId);
                contactDao.insertContactsFromHotel(hotel);
                addressDao.insertAddressFromHotel(hotel);
                amenityDao.insertAmenitiesFromHotel(hotel);

                List<Room> _rooms = new RoomGenerator(hotel, roomAmenities).generateRooms(100);
                rooms.addAll(_rooms);
                roomDao.insertRooms(_rooms);
            }
        });

        loader.loadFromJsonFile("sampleData/Employees.json", Employee[].class, employees -> {
            for (Employee employee : employees) {
                int employeeId = employeeDao.insertEmployee(employee);
                employee.setEmployeeId(employeeId);
                addressDao.insertAddressFromEmployee(employee);
            }
        });

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            int customerId = customerDao.insertCustomer(customer);
            customer.setCustomerId(customerId);
            addressDao.insertAddressFromCustomer(customer);
            customers.set(i, customer);
        }

        bookingDao.insertBookings(new BookingGenerator(customers, rooms).generateBookings());

    }

    /**
     * Perform shutdown actions here.
     */
    @PreDestroy
    public void onShutdown() {

    }
}
