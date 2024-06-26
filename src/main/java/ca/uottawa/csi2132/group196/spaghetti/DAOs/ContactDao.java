package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Contact;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDao {
    private static final String INSERT_CONTACT_SQL = "INSERT INTO contacts (name, email, phone_number) VALUES (?, ?, ?)";
    private static final String INSERT_CONTACT_RELATION_HOTEL_CHAIN_SQL = "INSERT INTO hotel_chain_contacts (chain_name, contact_id) VALUES (?, ?)";
    private static final String INSERT_CONTACT_RELATION_HOTEL_SQL = "INSERT INTO hotel_contacts (hotel_id, contact_id) VALUES (?, ?)";
    private static final String SELECT_CONTACT_BY_ID_SQL = "SELECT * FROM contacts WHERE contact_id = ?";
    private static final String SELECT_CONTACTS_FOR_HOTEL_CHAIN_SQL = "SELECT contactInst.* FROM hotel_chain_contacts contactRelInst LEFT JOIN contacts contactInst ON contactRelInst.contact_id = contactInst.contact_id WHERE contactRelInst.chain_name = ?";
    private static final String SELECT_CONTACTS_FOR_HOTEL_SQL = "SELECT contactInst.* FROM hotel_contacts contactRelInst LEFT JOIN contacts contactInst ON contactRelInst.contact_id = contactInst.contact_id WHERE contactRelInst.hotel_id = ?";
    private static final String UPDATE_CONTACT_SQL = "UPDATE contacts SET name = ?, email = ?, phone_number = ? WHERE contact_id = ?";
    private static final String DELETE_CONTACT_SQL = "DELETE FROM contacts WHERE contact_id = ?";

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

    public Contact getContactById(int contactId) {
        FieldMapper<Contact> mapper = new FieldMapper<>(database.getDataSource(), SELECT_CONTACT_BY_ID_SQL, Contact.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "contact_id"));
        return mapper.findObject(contactId);
    }

    public List<Contact> getContactsForHotelChain(String chainName) {
        FieldMapper<Contact> mapper = new FieldMapper<>(database.getDataSource(), SELECT_CONTACTS_FOR_HOTEL_CHAIN_SQL, Contact.class);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.execute(chainName);
    }

    public List<Contact> getContactsForHotel(int hotelId) {
        FieldMapper<Contact> mapper = new FieldMapper<>(database.getDataSource(), SELECT_CONTACTS_FOR_HOTEL_SQL, Contact.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotelId);
    }
    
    private void updateContact(int contactId, Contact updatedContact) {
        if (updatedContact == null) return;
        Contact originalContact = getContactById(contactId);
        if (originalContact == null)return;
        
        updatedContact.fillFromInstance(originalContact);
        database.update(UPDATE_CONTACT_SQL, updatedContact.getName(), updatedContact.getEmail(), updatedContact.getPhoneNumber(), originalContact.getContactId());
    }
    
    public void deleteContact(int contactId) {
        database.update(DELETE_CONTACT_SQL, contactId);
    }

}
