package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;

@Repository
public class CustomerDao {
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (name, id_type) VALUES (?, ?)";
    private static final String SELECT_CUSTOMER_SQL = "SELECT * FROM customer WHERE customer_id = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET id_type = ?, name = ? WHERE customer_id = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE customer_id = ?";

    private final JdbcTemplate database;

    public CustomerDao(JdbcTemplate database) {
        this.database = database;
    }

    public int insertCustomer(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        database.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getIdType());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) return -1;
        Number key = (Number) keys.get("customer_id");
        if (key == null) return -1;
        return key.intValue();
    }

    public Customer getCustomerById(int customerId) {
        FieldMapper<Customer> mapper = new FieldMapper<>(database.getDataSource(), SELECT_CUSTOMER_SQL, Customer.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.findObject(customerId);
    }

    public void updateCustomer(Customer updatedCustomer) {
        if (updatedCustomer == null) return;
        Customer originalCustomer = getCustomerById(updatedCustomer.getCustomerId());
        if (originalCustomer == null) return;
        updatedCustomer.fillFromInstance(originalCustomer);
        database.update(UPDATE_CUSTOMER_SQL, updatedCustomer.getName(), updatedCustomer.getIdType(), originalCustomer.getCustomerId());
    }

    public void deleteCustomerById(int customerId) {
        database.update(DELETE_CUSTOMER_SQL, customerId);
    }
}
