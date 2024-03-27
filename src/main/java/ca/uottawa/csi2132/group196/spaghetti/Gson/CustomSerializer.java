package ca.uottawa.csi2132.group196.spaghetti.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomSerializer {
    public static Gson getCustomSerializer() {
        return new GsonBuilder().addSerializationExclusionStrategy(new DoNotSerializeStrategy()).create();
    }
}
