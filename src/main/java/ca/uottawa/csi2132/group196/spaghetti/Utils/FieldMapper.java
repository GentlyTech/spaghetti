package ca.uottawa.csi2132.group196.spaghetti.Utils;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldMapper<T> extends MappingSqlQuery<T> {
    Class<T> objectType;

    public FieldMapper(DataSource dataSource, String query, Class<T> objectType) {
        super(dataSource, query);
        this.objectType = objectType;
    }

    @Override
    protected T mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            T result = objectType.getDeclaredConstructor().newInstance();

            PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(result);
            Field[] fields = objectType.getFields();
            for (Field field : fields) {
                if (field.getAnnotation(MappedField.class) == null) continue;


            }

            return result;
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
