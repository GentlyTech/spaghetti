package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.*;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    JdbcTemplate database;
    Gson serializer;
    CustomerDao customerDao;

    public CustomerController(JdbcTemplate database, Gson serializer, CustomerDao customerDao) {
        this.database = database;
        this.serializer = serializer;
        this.customerDao = customerDao;
    }
}
