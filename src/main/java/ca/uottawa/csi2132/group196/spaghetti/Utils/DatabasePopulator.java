package ca.uottawa.csi2132.group196.spaghetti.Utils;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.function.Consumer;

public class DatabasePopulator {
    Gson serializer;
    
    public DatabasePopulator(Gson serializer) {
        this.serializer = serializer;
    }
    
    public <T> void populateFromJsonFile(String path, Class<T> dataType, Consumer<T> callback) {
        InputStream inputStream;
        Reader reader;

        try {
            File file = new File(path);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                Resource resource = new ClassPathResource(path);
                if (!resource.exists()) {
                    return;
                }
                inputStream = resource.getInputStream();
            }
            reader = new InputStreamReader(inputStream);

            T data = serializer.fromJson(reader, dataType);

            if (callback != null) {
                callback.accept(data);
            }

        } catch (IOException ignored) {

        }
    }
}
