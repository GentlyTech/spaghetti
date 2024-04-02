package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Employee;
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
public class EmployeeDao {
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (name, sin) VALUES (?, ?)";
    private static final String SELECT_EMPLOYEE_SQL = "SELECT * FROM employee WHERE employee_id = ?";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET name = ?, sin = ? WHERE employee_id = ?";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE employee_id = ?";

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

    public Employee getEmployeeById(int employeeId) {
        FieldMapper<Employee> mapper = new FieldMapper<>(database.getDataSource(), SELECT_EMPLOYEE_SQL, Employee.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "employee_id"));
        return mapper.findObject(employeeId);
    }
    
    public void updateEmployee(Employee updatedEmployee) {
        if (updatedEmployee == null) return;
        Employee originalEmployee = getEmployeeById(updatedEmployee.getEmployeeId());
        if (originalEmployee == null) return;
        
        updatedEmployee.fillFromInstance(originalEmployee);
        database.update(UPDATE_EMPLOYEE_SQL, updatedEmployee.getName(), updatedEmployee.getSinNumber(), originalEmployee.getEmployeeId());
    }

    public void deleteEmployeeById(int employeeId) {
        database.update(DELETE_EMPLOYEE_SQL, employeeId);
    }
}
