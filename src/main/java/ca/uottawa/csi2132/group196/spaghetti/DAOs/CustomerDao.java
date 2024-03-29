package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    private final JdbcTemplate database;

    public CustomerDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertCustomer(Customer customer) {
        return;
    }

    public Customer getCustomerById(int customerId) {
        return null;
    }
}
