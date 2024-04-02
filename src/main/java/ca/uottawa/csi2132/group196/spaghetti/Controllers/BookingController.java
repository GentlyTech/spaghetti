package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.BookingUpdateQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @PostMapping("/book")
    public String insertBooking(@RequestBody Booking booking) {
        return null;
    }
    
    @PostMapping("/update")
    public String updateBooking(@RequestBody BookingUpdateQuery query) {
        return null;
    }

}
