package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.EmployeeDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Employee;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    JdbcTemplate database;
    Gson serializer;
    EmployeeDao employeeDao;

    public EmployeeController(JdbcTemplate database, Gson serializer, EmployeeDao employeeDao) {
        this.database = database;
        this.serializer = serializer;
        this.employeeDao = employeeDao;
    }

    @PostMapping("/register")
    public String registerEmployee(@RequestBody Employee employee) {
        return null;
    }

    @PutMapping("/update/{employeeId}")
    public String updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        return null;
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeDao.deleteEmployeeById(employeeId);
    }

    @GetMapping("/info/{employeeId}")
    public String getEmployeeInfo(@PathVariable int employeeId) {
        return serializer.toJson(employeeDao.getEmployeeById(employeeId));
    }
}
