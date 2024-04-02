package ca.uottawa.csi2132.group196.spaghetti.Utils;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Gson.DoNotSerialize;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AutoFillableClass<T> {
    @DoNotSerialize
    private final Class<T> classType = (Class<T>) this.getClass();
    
    public void fillFromInstance(T otherInstance) {
        try {
            T defaultInstance = classType.getConstructor().newInstance();
            
            Field[] fields = classType.getDeclaredFields();

            for (Field field : fields) {
                Autofill autofillAnnotation = field.getAnnotation(Autofill.class);
                if (autofillAnnotation == null) continue;

                field.setAccessible(true);
                
                Object currentValue = field.get(this);
                Object defaultValue = field.get(defaultInstance);
                
                if (currentValue == null || currentValue.equals(defaultValue)) {
                    field.set(this, field.get(otherInstance));
                }
            }
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }
}
