package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Contact;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDao {
    private final String INSERT_CONTACT_SQL = "INSERT INTO contacts (name, email, phone_number) VALUES (?, ?, ?)";
    private final String INSERT_CONTACT_RELATION_HOTEL_CHAIN_SQL = "INSERT INTO hotel_chain_contacts (chain_name, contact_id) VALUES (?, ?)";
    private final String INSERT_CONTACT_RELATION_HOTEL_SQL = "INSERT INTO hotel_contacts (hotel_id, contact_id) VALUES (?, ?)";

    private final JdbcTemplate database;

    public ContactDao(JdbcTemplate database) {
        this.database = database;
    }

    public int insertContact(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        database.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getPhoneNumber());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) return -1;
        Number key = (Number) keys.get("contact_id");
        if (key == null) return -1;
        return key.intValue();
    }

    public int[] insertContactsFromHotelChain(HotelChain hotelChain) {
        List<Contact> contacts = hotelChain.getContacts();
        int[] contactIds = new int[contacts.size()];

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            int contactId = insertContact(contact);
            database.update(INSERT_CONTACT_RELATION_HOTEL_CHAIN_SQL, hotelChain.getChainName(), contactId);
            contactIds[i] = contactId;
        }
        
        return contactIds;
    }

    public int[] insertContactsFromHotel(Hotel hotel) {
        List<Contact> contacts = hotel.getContacts();
        int[] contactIds = new int[contacts.size()];

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            int contactId = insertContact(contact);
            database.update(INSERT_CONTACT_RELATION_HOTEL_SQL, hotel.getHotelId(), contactId);
            contactIds[i] = contactId;
        }

        return contactIds;
    }

}
