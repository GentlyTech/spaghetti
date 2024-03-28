package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.HotelChain;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.util.logging.Logger;

@SpringBootApplication
public class Spaghetti {
    JdbcTemplate database;
    Gson serializer;

    public Spaghetti(JdbcTemplate database, Gson serializer) {
        this.database = database;
        this.serializer = serializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Spaghetti.class, args);
    }

    /**
     * Perform init actions here.
     */
    @PostConstruct
    public void onStartup() {
        InputStream inputStream;
        Reader reader;

        // Init HotelChains
        try {
            inputStream = new ClassPathResource("sampleData/HotelChains.json").getInputStream();
            reader = new InputStreamReader(inputStream);
            HotelChain[] hotelChains = serializer.fromJson(reader, HotelChain[].class);
            for (HotelChain hotelChain : hotelChains) {
                
            }
        }
        catch (IOException ignored) {
            
        }

    }

    /**
     * Perform shutdown actions here.
     */
    @PreDestroy
    public void onShutdown() {

    }
}
