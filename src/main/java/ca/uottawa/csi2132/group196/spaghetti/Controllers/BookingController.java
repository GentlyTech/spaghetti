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
    public void insertBooking(@RequestBody Booking booking) {
        bookingDao.insertBooking(booking);
    }

    @PostMapping("/update")
    public void updateBooking(@RequestBody BookingUpdateQuery query) {
        if (query == null) return;
        BookingId id = query.getBookingId();
        if (id == null) return;
        Booking booking = query.getUpdatedBooking();
        if (booking == null) return;

        bookingDao.updateBooking(id.getRoomNumber(), id.getHotelId(), id.getCheckInDate(), id.getCheckOutDate(), booking);
    }

    @DeleteMapping("/delete")
    public void deleteBooking(@RequestBody BookingId query) {
        bookingDao.deleteBooking(query.getRoomNumber(), query.getHotelId(), query.getCheckInDate(), query.getCheckOutDate());
    }

    @GetMapping("/get/hotelId/{hotelId}")
    public String getBookingsByHotel(@PathVariable int hotelId) {
        return serializer.toJson(bookingDao.getBookingsByHotel(hotelId));
    }

    @GetMapping("/getDetailed/hotelId/{hotelId}")
    public String getDetailedBookingsByHotel(@PathVariable int hotelId) {
        return serializer.toJson(bookingDao.getDetailedBookingsByHotel(hotelId));
    }
}
