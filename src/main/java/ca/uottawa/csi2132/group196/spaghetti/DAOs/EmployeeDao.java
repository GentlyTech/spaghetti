package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (name, sin) VALUES (?, ?)";

    private final JdbcTemplate database;

    public EmployeeDao(JdbcTemplate database) {
        this.database = database;
    }

    public int insertEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        database.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSinNumber());
            return statement;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys == null) return -1;
        Number employeeId = (Number) keys.get("employee_id");
        if (employeeId == null) return -1;
        return employeeId.intValue();
    }
}
