package ca.uottawa.csi2132.group196.spaghetti.Gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Hints to the Gson serializer that the annotated field or type should be excluded from serialization.
 * <p/>
 * Note that for this to take effect, you need to use the GsonBuilder with addSerializationExclusionStrategy() and pass an instance of the DoNotSerializeStrategy into it.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface DoNotSerialize {}
