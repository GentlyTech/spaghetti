package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Role;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class RoleDao {
    private static final String INSERT_ROLE_SQL = "INSERT INTO roles (name, description) VALUES (?, ?)";
    private static final String INSERT_EMPLOYEE_ROLE_SQL = "INSERT INTO employee_roles (employee_id, role_name) VALUES (?, ?)";
    private static final String SELECT_ALL_ROLES_SQL = "SELECT * FROM ROLE";
    private static final String SELECT_EMPLOYEE_ROLES_SQL = "SELECT roleInst.* FROM roles roleInst LEFT JOIN employee_roles employeeRelInst ON roleInst.name = employeeRelInst.role_name WHERE employeeRelInst.employee_id = ?";

    private final JdbcTemplate database;

    public RoleDao(JdbcTemplate database) {
        this.database = database;
    }

    public void insertRole(Role role) {
        database.update(INSERT_ROLE_SQL, role.getName(), role.getDescription());
    }

    public void insertEmployeeRoleRelation(int employeeId, String roleName) {
        database.update(INSERT_EMPLOYEE_ROLE_SQL, employeeId, roleName);
    }

    public List<Role> getRoles() {
        FieldMapper<Role> mapper = new FieldMapper<>(database.getDataSource(), SELECT_ALL_ROLES_SQL, Role.class);
        return mapper.execute();
    }

    public List<Role> getEmployeeRolesByEmployeeId(int employeeId) {
        FieldMapper<Role> mapper = new FieldMapper<>(database.getDataSource(), SELECT_EMPLOYEE_ROLES_SQL, Role.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "employee_id"));
        return mapper.execute(employeeId);
    }
}
