package ca.uottawa.csi2132.group196.spaghetti;

import ca.uottawa.csi2132.group196.spaghetti.Repositories.HotelChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Spaghetti {

	private final HotelChainRepository hotelChainRepository;

    public Spaghetti(final HotelChainRepository hotelChainRepository) {
        this.hotelChainRepository = hotelChainRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Spaghetti.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping("/hotelChains")
	public String enumerateHotelChains() {
		return hotelChainRepository.findAll().toString();
	}
}
