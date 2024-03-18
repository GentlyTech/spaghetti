package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Repositories.HotelChainRepository;
import ca.uottawa.csi2132.group196.spaghetti.Serialization.DoNotSerializeStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/hotelChain")
public class HotelChainController {

    private final HotelChainRepository hotelChainRepository;

    public HotelChainController(final HotelChainRepository hotelChainRepository) {
        this.hotelChainRepository = hotelChainRepository;
    }

    @GetMapping("/info")
    public String enumerateHotelChains(@RequestParam(name = "name", required = false) String chainName) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new DoNotSerializeStrategy()).create();
        if (chainName == null || chainName.isEmpty()) {
            return gson.toJson(hotelChainRepository.findAll());
        }
        HotelChain rawResponse = hotelChainRepository.getHotelChainByChainName(chainName);
        if (rawResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return gson.toJson(rawResponse);
    }

    @GetMapping("/count")
    public String getHotelCount(@RequestParam(name = "name") String chainName) {
        int count = hotelChainRepository.getHotelCountByChainName(chainName);
        return new Gson().toJson(count);
    }
}
