package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.*;
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
public class AddressDao {
    private static final String INSERT_ADDRESS_SQL = "INSERT INTO addresses (alias, street, city, province, postal_code, country) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ADDRESS_RELATION_HOTEL_CHAIN_SQL = "INSERT INTO hotel_chain_addresses (chain_name, address_id) VALUES (?, ?)";
    private static final String INSERT_ADDRESS_RELATION_HOTEL_SQL = "INSERT INTO hotel_addresses (hotel_id, address_id) VALUES (?, ?)";
    private static final String INSERT_ADDRESS_RELATION_CUSTOMER_SQL = "INSERT INTO customer_addresses (customer_id, address_id) VALUES (?, ?)";
    private static final String INSERT_ADDRESS_RELATION_EMPLOYEE_SQL = "INSERT INTO employee_addresses (employee_id, address_id) VALUES (?, ?)";
    private static final String SELECT_ADDRESS_BY_ID_SQL = "SELECT * FROM addresses WHERE address_id = ?";
    private static final String SELECT_ADDRESSES_FOR_HOTEL_CHAIN_SQL = "SELECT addressInst.* FROM hotel_chain_addresses addressRelInst LEFT JOIN addresses addressInst ON addressRelInst.address_id = addressInst.address_id WHERE addressRelInst.chain_name = ?";
    private static final String SELECT_ADDRESS_FOR_HOTEL_SQL = "SELECT addressInst.* FROM hotel_addresses addressRelInst LEFT JOIN addresses addressInst ON addressRelInst.address_id = addressInst.address_id WHERE addressRelInst.hotel_id = ?";
    private static final String SELECT_ADDRESS_FOR_CUSTOMER_SQL = "SELECT addressInst.* FROM customer_addresses addressRelInst LEFT JOIN addresses addressInst ON addressRelInst.address_id = addressInst.address_id WHERE addressRelInst.customer_id = ?";
    private static final String SELECT_ADDRESS_FOR_EMPLOYEE_SQL = "SELECT addressInst.* FROM employee_addresses addressRelInst LEFT JOIN addresses addressInst ON addressRelInst.address_id = addressInst.address_id WHERE addressRelInst.employee_id = ?";
    private static final String UPDATE_ADDRESS_SQL = "UPDATE addresses SET alias = ?, street = ?, city = ?, province = ?, postal_code = ?, country = ? WHERE address_id = ?";
    private static final String DELETE_ADDRESS_SQL = "DELETE FROM addresses WHERE address_id = ?";

    private final JdbcTemplate database;

    public AddressDao(JdbcTemplate database) {
        this.database = database;
    }

    public int insertAddress(Address address) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        database.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_ADDRESS_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getAlias());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getProvince());
            statement.setString(5, address.getPostalCode());
            statement.setString(6, address.getCountry());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) return -1;
        Number key = (Number) keys.get("address_id");
        if (key == null) return -1;
        return key.intValue();
    }

    public int[] insertAddressesFromHotelChain(HotelChain hotelChain) {
        List<Address> addresses = hotelChain.getAddresses();
        int[] addressIds = new int[addresses.size()];

        for (int i = 0; i < addresses.size(); i++) {
            Address address = addresses.get(i);
            int addressId = insertAddress(address);
            database.update(INSERT_ADDRESS_RELATION_HOTEL_CHAIN_SQL, hotelChain.getChainName(), addressId);
            addressIds[i] = addressId;
        }

        return addressIds;
    }

    public int insertAddressFromHotel(Hotel hotel) {
        Address address = hotel.getAddress();

        int addressId = insertAddress(address);
        database.update(INSERT_ADDRESS_RELATION_HOTEL_SQL, hotel.getHotelId(), addressId);

        return addressId;
    }

    public int insertAddressFromCustomer(Customer customer) {
        Address address = customer.getAddress();

        int addressId = insertAddress(address);
        database.update(INSERT_ADDRESS_RELATION_CUSTOMER_SQL, customer.getCustomerId(), addressId);

        return addressId;
    }

    public int insertAddressFromEmployee(Employee employee) {
        Address address = employee.getAddress();

        int addressId = insertAddress(address);
        database.update(INSERT_ADDRESS_RELATION_EMPLOYEE_SQL, employee.getEmployeeId(), addressId);

        return addressId;
    }

    public Address getAddressById(int addressId) {
        FieldMapper<Address> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ADDRESS_BY_ID_SQL, Address.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "address_id"));
        return mapper.findObject(addressId);
    }

    public List<Address> getAddressesForHotelChain(String chainName) {
        FieldMapper<Address> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ADDRESSES_FOR_HOTEL_CHAIN_SQL, Address.class);
        mapper.declareParameter(new SqlParameterValue(Types.LONGVARCHAR, "chain_name"));
        return mapper.execute(chainName);
    }
    
    public Address getAddressForHotel(int hotelId) {
        FieldMapper<Address> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ADDRESS_FOR_HOTEL_SQL, Address.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.findObject(hotelId);
    }

    public Address getAddressForCustomer(int customerId) {
        FieldMapper<Address> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ADDRESS_FOR_CUSTOMER_SQL, Address.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.findObject(customerId);
    }

    public Address getAddressForEmployee(int employeeId) {
        FieldMapper<Address> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ADDRESS_FOR_EMPLOYEE_SQL, Address.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "employee_id"));
        return mapper.findObject(employeeId);
    }
    
    private void updateAddress(Address updatedAddress, Address existingAddress) {
        if (updatedAddress == null || existingAddress == null) return;

        updatedAddress.fillFromInstance(existingAddress);
        database.update(UPDATE_ADDRESS_SQL, updatedAddress.getAlias(), updatedAddress.getStreet(), updatedAddress.getCity(), updatedAddress.getProvince(), updatedAddress.getPostalCode(), updatedAddress.getCountry(), existingAddress.getAddressId());
    }
    
    public void updateCustomerAddress(int customerId, Address address) {
        updateAddress(address, getAddressForCustomer(customerId));
    }

    public void updateEmployeeAddress(int employeeId, Address address) {
        updateAddress(address, getAddressForEmployee(employeeId));
    }

    public void updateHotelAddress(int hotelId, Address address) {
        updateAddress(address, getAddressForHotel(hotelId));
    }

    public void updateHotelChainAddress(int addressId, Address address) {
        updateAddress(address, getAddressById(addressId));
    }
    
    public void deleteAddress(int addressId) {
        database.update(DELETE_ADDRESS_SQL, addressId);
    }
}
