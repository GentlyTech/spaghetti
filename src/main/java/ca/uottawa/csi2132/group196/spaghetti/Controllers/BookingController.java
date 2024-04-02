package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import ca.uottawa.csi2132.group196.spaghetti.DAOs.BookingDao;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.BookingId;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.BookingUpdateQuery;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    JdbcTemplate database;
    Gson serializer;
    BookingDao bookingDao;

    public BookingController(JdbcTemplate database, Gson serializer, BookingDao bookingDao) {
        this.database = database;
        this.serializer = serializer;
        this.bookingDao = bookingDao;
    }

    @GetMapping("/get/{customerId}")
    public String getBookingsForCustomer(@PathVariable int customerId) {
        return serializer.toJson(bookingDao.getBookingsByCustomerId(customerId));
    }

    @PostMapping("/book")
    public String insertBooking(@RequestBody Booking booking) {
        return null;
    }

    @PostMapping("/update")
    public String updateBooking(@RequestBody BookingUpdateQuery query) {
        return null;
    }

    @DeleteMapping("/delete")
    public void deleteBooking(@RequestBody BookingId query) {
        bookingDao.deleteBooking(query.getRoomNumber(), query.getCustomerId(), query.getHotelId(), query.getCheckInDate(), query.getCheckOutDate());
    }

}
