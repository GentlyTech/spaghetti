package ca.uottawa.csi2132.group196.spaghetti.Mappers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmenityMapper extends MappingSqlQuery<Amenity> {

    public AmenityMapper(DataSource dataSource, String query) {
        super(dataSource, query);
    }

    @Override
    protected Amenity mapRow(ResultSet rs, int rowNum) {
        Amenity amenity = new Amenity();

        try {
            amenity.setName(rs.getString("name"));
        } catch (SQLException ignored) {

        }

        try {
            amenity.setDescription(rs.getString("description"));
        } catch (SQLException ignored) {

        }

        return amenity;
    }
}