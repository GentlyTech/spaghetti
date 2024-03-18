package ca.uottawa.csi2132.group196.spaghetti.Serialization;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class DoNotSerializeStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        return field.getAnnotation(DoNotSerialize.class) != null;
    }
    
    @Override
    public boolean shouldSkipClass(Class<?> cls) {
        return cls.getAnnotation(DoNotSerialize.class) != null;
    }
}
