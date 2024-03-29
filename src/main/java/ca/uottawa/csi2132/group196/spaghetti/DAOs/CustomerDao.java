package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
public class CustomerDao {
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (name, id_type) VALUES (?, ?)";
    private static final String SELECT_CUSTOMER_SQL = "";

    private final JdbcTemplate database;

    public CustomerDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertCustomer(Customer customer) {
        database.update(INSERT_CUSTOMER_SQL, customer.getName(), customer.getAddress());
    }

    public Customer getCustomerById(int customerId) {
        FieldMapper<Customer> mapper = new FieldMapper<>(database.getDataSource(), SELECT_CUSTOMER_SQL, Customer.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.findObject(customerId);
    }
}
