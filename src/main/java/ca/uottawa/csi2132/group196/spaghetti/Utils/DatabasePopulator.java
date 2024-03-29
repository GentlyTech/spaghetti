package ca.uottawa.csi2132.group196.spaghetti.Utils;

import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class DatabasePopulator {
    Gson serializer;
    Logger logger;

    public DatabasePopulator(Gson serializer) {
        this.serializer = serializer;
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
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
                    logger.warning(String.format("The file at '%s' was not found. Skipping...", path));
                    return;
                }
                inputStream = resource.getInputStream();
            }
            reader = new InputStreamReader(inputStream);

            T data = serializer.fromJson(reader, dataType);

            if (callback != null) {
                callback.accept(data);
            }

        } catch (IOException ex) {
            logger.warning(ex.toString());
        }
    }

    public <T> T populateFromJsonFile(String path, Class<T> dataType) {
        InputStream inputStream;
        Reader reader;

        try {
            File file = new File(path);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                Resource resource = new ClassPathResource(path);
                if (!resource.exists()) {
                    logger.warning(String.format("The file at '%s' was not found. Skipping...", path));
                    return null;
                }
                inputStream = resource.getInputStream();
            }
            reader = new InputStreamReader(inputStream);

            return serializer.fromJson(reader, dataType);

        } catch (IOException ex) {
            logger.warning(ex.toString());
            return null;
        }
    }
}
