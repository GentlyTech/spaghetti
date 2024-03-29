package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Role;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper extends MappingSqlQuery<Role> {

    public RoleMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected Role mapRow(ResultSet rs, int rowNum) {
        Role role = new Role();

        try {
            role.setName(rs.getString("name"));
        } catch (SQLException ignored) {

        }

        try {
            role.setDescription(rs.getString("description"));
        } catch (SQLException ignored) {

        }

        return role;
    }
}