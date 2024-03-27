package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotelChain")
public class HotelChainController {
    JdbcTemplate database;

    public HotelChainController(JdbcTemplate database) {
        this.database = database;
    }

    @GetMapping({"/info", "/info/"})
    public String getAllChainInfo() {
        return "Empty";
    }

    @GetMapping({"/info/{chain_name}", "/info/{chain_name}/"})
    public String getChainInfo(@PathVariable String chain_name) {
        return chain_name;
    }
}
