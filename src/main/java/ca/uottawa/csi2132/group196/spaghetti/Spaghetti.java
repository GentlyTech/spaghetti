package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AddressDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ContactDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelChainDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Utils.DatabasePopulator;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Spaghetti {
    JdbcTemplate database;
    Gson serializer;

    AddressDao addressDao;
    ContactDao contactDao;
    HotelChainDao hotelChainDao;
    HotelDao hotelDao;

    public Spaghetti(JdbcTemplate database, Gson serializer, AddressDao addressDao, ContactDao contactDao, HotelChainDao hotelChainDao, HotelDao hotelDao) {
        this.database = database;
        this.serializer = serializer;
        this.addressDao = addressDao;
        this.contactDao = contactDao;
        this.hotelChainDao = hotelChainDao;
        this.hotelDao = hotelDao;
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

        populator.populateFromJsonFile("sampleData/HotelChains.json", HotelChain[].class, data -> {
            for (HotelChain hotelChain : data) {
                hotelChainDao.insertHotelChain(hotelChain);
                contactDao.insertContactsFromHotelChain(hotelChain);
                addressDao.insertAddressFromHotelChain(hotelChain);
            }
        });

        populator.populateFromJsonFile("sampleData/Hotels.json", Hotel[].class, data -> {
            for (Hotel hotel : data) {
                int hotelId = hotelDao.insertHotel(hotel);
                hotel.setHotelId(hotelId);
                contactDao.insertContactsFromHotel(hotel);
                addressDao.insertAddressFromHotel(hotel);
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
