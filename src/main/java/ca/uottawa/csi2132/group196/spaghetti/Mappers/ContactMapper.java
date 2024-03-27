package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Contact;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper extends MappingSqlQuery<Contact> {


    public ContactMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected Contact mapRow(ResultSet rs, int rowNum) {
        Contact contact = new Contact();
        
        try {
            contact.setContactId(rs.getInt("contact_id"));
        } catch (SQLException ignored) {

        }

        try {
            contact.setName(rs.getString("name"));
        } catch (SQLException ignored) {

        }

        try {
            contact.setEmail(rs.getString("email"));
        } catch (SQLException ignored) {

        }

        try {
            contact.setPhoneNumber(rs.getString("phone_number"));
        } catch (SQLException ignored) {

        }

        return contact;
    }
}