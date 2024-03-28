package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AddressDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ContactDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelChainDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Address;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Contact;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.AddressMapper;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.ContactMapper;
import ca.uottawa.csi2132.group196.spaghetti.Mappers.HotelChainMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Types;
import java.util.List;

@RestController
@RequestMapping("/hotelChain")
public class HotelChainController {
    JdbcTemplate database;
    Gson serializer;

    AddressDao addressDao;
    ContactDao contactDao;
    HotelChainDao hotelChainDao;
    HotelDao hotelDao;

    public HotelChainController(JdbcTemplate database, Gson serializer, AddressDao addressDao, ContactDao contactDao, HotelChainDao hotelChainDao, HotelDao hotelDao) {
        this.database = database;
        this.serializer = serializer;
        this.addressDao = addressDao;
        this.contactDao = contactDao;
        this.hotelChainDao = hotelChainDao;
        this.hotelDao = hotelDao;
    }

    @GetMapping({"/info", "/info/"})
    public String getHotelChainInfoAll() {
        // If we wanted to we could do something like `SELECT hotelChainInst.chain_name AS chain_name, COUNT(hotelInst.hotel_id) AS hotel_count FROM hotel_chain hotelChainInst FULL JOIN hotel hotelInst ON hotelChainInst.chain_name = hotelInst.owner GROUP BY hotelChainInst.chain_name;` but breaking the queries up is cleaner
        List<HotelChain> results = hotelChainDao.getAllHotelChains();
        for (int i = 0; i < results.size(); i++) {
            HotelChain hotelChain = results.get(i);
            hotelChain.setAddresses(addressDao.getAddressesForHotelChain(hotelChain.getChainName()));
            hotelChain.setContacts(contactDao.getContactsForHotelChain(hotelChain.getChainName()));
            hotelChain.setHotelCount(hotelDao.getHotelCountByChainName(hotelChain.getChainName()));
            results.set(i, hotelChain);
        }
        return serializer.toJson(results);
    }

    @GetMapping({"/info/{chain_name}", "/info/{chain_name}/"})
    public String getHotelChainInfo(@PathVariable String chain_name) {
        HotelChain result = hotelChainDao.getHotelChainByChainName(chain_name);

        if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        result.setContacts(contactDao.getContactsForHotelChain(result.getChainName()));
        result.setAddresses(addressDao.getAddressesForHotelChain(result.getChainName()));
        result.setHotelCount(hotelDao.getHotelCountByChainName(result.getChainName()));

        return serializer.toJson(result);
    }
}
