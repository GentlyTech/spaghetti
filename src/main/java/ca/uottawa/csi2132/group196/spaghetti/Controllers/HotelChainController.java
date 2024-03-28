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
        List<HotelChain> results = new HotelChainMapper(database.getDataSource(), "SELECT hotelChainInst.chain_name AS chain_name, COUNT(hotelInst.hotel_id) AS hotel_count FROM hotel_chain hotelChainInst FULL JOIN hotel hotelInst ON hotelChainInst.chain_name = hotelInst.owner GROUP BY hotelChainInst.chain_name;").execute();
        return serializer.toJson(results);
    }

    @GetMapping({"/info/{chain_name}", "/info/{chain_name}/"})
    public String getHotelChainInfo(@PathVariable String chain_name) {
        HotelChainMapper hotelChainMapper = new HotelChainMapper(database.getDataSource(), "SELECT hotelChainInst.chain_name AS chain_name, COUNT(hotelInst.hotel_id) AS hotel_count FROM hotel_chain hotelChainInst LEFT JOIN hotel hotelInst ON hotelChainInst.chain_name = hotelInst.owner WHERE chain_name = ? GROUP BY hotelChainInst.chain_name;");
        hotelChainMapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        HotelChain result = hotelChainMapper.findObject(chain_name);

        if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ContactMapper contactMapper = new ContactMapper(database.getDataSource(), "SELECT contactInst.* FROM hotel_chain_contacts hotelChainInst LEFT JOIN contacts contactInst ON hotelChainInst.contact_id = contactInst.contact_id WHERE hotelChainInst.chain_name = ? GROUP BY contactInst.contact_id;");
        contactMapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        List<Contact> contacts = contactMapper.execute(chain_name);

        result.setContacts(contacts);

        AddressMapper addressMapper = new AddressMapper(database.getDataSource(), "SELECT addressInst.* FROM hotel_chain_addresses hotelChainInst LEFT JOIN addresses addressInst ON hotelChainInst.address_id = addressInst.address_id WHERE hotelChainInst.chain_name = ? GROUP BY addressInst.address_id;");
        addressMapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        List<Address> addresses = addressMapper.execute(chain_name);

        result.setAddresses(addresses);

        return serializer.toJson(result);
    }
}
