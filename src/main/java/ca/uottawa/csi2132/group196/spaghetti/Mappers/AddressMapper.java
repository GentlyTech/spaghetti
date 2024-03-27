package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Address;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper extends MappingSqlQuery<Address> {

    public AddressMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected Address mapRow(ResultSet rs, int rowNum) {
        Address address = new Address();

        try {
            address.setAddressId(rs.getInt("address_id"));
        } catch (SQLException ignored) {

        }

        try {
            address.setCity(rs.getString("city"));
        } catch (SQLException ignored) {

        }

        try {
            address.setCountry(rs.getString("country"));
        } catch (SQLException ignored) {

        }

        try {
            address.setPostalCode(rs.getString("postal_code"));
        } catch (SQLException ignored) {

        }

        try {
            address.setProvince(rs.getString("province"));
        } catch (SQLException ignored) {

        }

        try {
            address.setStreet(rs.getString("street"));
        } catch (SQLException ignored) {

        }

        return address;
    }
}