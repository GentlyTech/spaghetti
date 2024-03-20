package ca.uottawa.csi2132.group196.spaghetti;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spaghetti {
    public static void main(String[] args) {
        SpringApplication.run(Spaghetti.class, args);
    }

    /**
     * Perform shutdown actions here.
     */
    @PreDestroy
    public void onShutdown() {

    }
}
