package ca.uottawa.csi2132.group196.spaghetti.Utils;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

            Field[] fields = objectType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                MappedField mappedFieldAnnotation = field.getAnnotation(MappedField.class);
                if (mappedFieldAnnotation == null) continue;

                String columnName = field.getName();
                if (mappedFieldAnnotation.value().isEmpty()) {
                    columnName = mappedFieldAnnotation.value();
                }

                String fieldType = field.getType().getTypeName();

                if (fieldType.equals(String.class.getName())) {
                    field.set(result, rs.getString(columnName));
                } else if (fieldType.equals(Boolean.class.getName()) || fieldType.equals("boolean")) {
                    field.set(result, rs.getBoolean(columnName));
                } else if (fieldType.equals(Integer.class.getName()) || fieldType.equals("int")) {
                    field.set(result, rs.getInt(columnName));
                } else if (fieldType.equals(Double.class.getName()) || fieldType.equals("double")) {
                    field.set(result, rs.getDouble(columnName));
                } else if (fieldType.equals(Float.class.getName()) || fieldType.equals("float")) {
                    field.set(result, rs.getFloat(columnName));
                } else if (fieldType.equals(LocalDateTime.class.getName())) {
                    field.set(result, rs.getDate(columnName));
                } else {
                    throw new UnsupportedOperationException(String.format("FieldMapper currently does not support the type '%s'", fieldType));
                }
            }

            return result;
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
