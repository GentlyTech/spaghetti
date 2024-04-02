package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @PostMapping("/update")
    public String setBookingStatus(@RequestBody Booking booking) {
        return null;
    }

}
