package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.AddressDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.ContactDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelChainDao;
import ca.uottawa.csi2132.group196.spaghetti.DAOs.HotelDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
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

    @GetMapping("/info")
    public String getHotelChainInfoAll() {
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

    @GetMapping("/info/{chain_name}")
    public String getHotelChainInfo(@PathVariable String chain_name) {
        HotelChain result = hotelChainDao.getHotelChainByChainName(chain_name);

        if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        result.setContacts(contactDao.getContactsForHotelChain(result.getChainName()));
        result.setAddresses(addressDao.getAddressesForHotelChain(result.getChainName()));
        result.setHotelCount(hotelDao.getHotelCountByChainName(result.getChainName()));

        return serializer.toJson(result);
    }

    @GetMapping("/info/chainNames")
    public String getHotelChains(){
        List<HotelChain> res = hotelChainDao.getAllHotelChainNames();
        return serializer.toJson(res);
    }
}
