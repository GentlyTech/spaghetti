package ca.uottawa.csi2132.group196.spaghetti.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSerializer {
    private static GsonBuilder cachedBuilder;

    @Bean
    public static Gson getCustomSerializer() {
        if (cachedBuilder == null) {
            cachedBuilder = new GsonBuilder().addSerializationExclusionStrategy(new DoNotSerializeStrategy());
        }
        return cachedBuilder.create();
    }
}
