package ca.uottawa.csi2132.group196.spaghetti;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spaghetti {
    public static void main(String[] args) {
        SpringApplication.run(Spaghetti.class, args);
    }

    @PostConstruct
    public void onStartup() {
        
    }

    /**
     * Perform shutdown actions here.
     */
    @PreDestroy
    public void onShutdown() {

    }
}
