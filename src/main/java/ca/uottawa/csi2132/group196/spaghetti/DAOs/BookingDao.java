package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Types;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDao {



    private final JdbcTemplate database;
    private static final String SELECT_BOOKING_BY_HOTEL_ROOM_SQL = "SELECT * FROM booking WHERE room_id = ? AND hotel_id = ?";
    private static final String SELECT_BOOKINGS_BY_CUSTOMER_SQL = "SELECT * FROM booking WHERE customer_id = ?";
    private static final String SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL = "SELECT * FROM booking WHERE customer_id = ? AND hotel_id = ?";
    private static final String INSERT_BOOKING_SQL = "INSERT INTO booking VALUES (?, ?, ?, ?)";
    private static final String UPDATE_BOOKING_STATUS_SQL = "UPDATE booking SET booking_status = ? WHERE hotel_id = ? AND customer_id = ? AND room_number = ?";
    private static final String SELECT_BOOKINGS_BY_HOTEL_ROOM = "SELECT * FROM booking WHERE booking.hotel_id = ? AND booking.room_number = ?";
    private static final String BOOKING_EXISTS_COUNT_SQL = "SELECT COUNT(*) FROM booking WHERE (booking.hotel_id = ? AND booking.room_number = ?) AND EXISTS(SELECT * FROM booking WHERE check_in_date > ? AND check_out_date > ?)";


    public BookingDao(JdbcTemplate database) {
        this.database = database;
    }


    public void insertBooking(Booking booking) {
        database.update(INSERT_BOOKING_SQL, booking.getRoomNumber()  , booking.getCustomerId(), booking.getHotelId(), booking.getBookingStatus(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getDamageFee());
    }

    public List<Booking> getBookingsByCustomer(Customer customer){
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKINGS_BY_CUSTOMER_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.execute(customer.getCustomerId());
    }

    public List<Booking> getBookingsByCustomerAndHotel(Customer customer, Hotel hotel){
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        mapper.declareParameter(new SqlParameter(Types.INTEGER, "hotel_id"));
        return mapper.execute(customer.getCustomerId(), hotel.getHotelId());
    }

    public void updateStatus(Booking booking, String newStatus) {
        database.update(UPDATE_BOOKING_STATUS_SQL, newStatus, booking.getHotelId(), booking.getCustomerId(), booking.getCustomerId());
    }

    public Boolean isBooked(Date checkIn, Date checkOut, Hotel hotel, Room room) {

        Date today = new Date(System.currentTimeMillis());

        // get all rooms from hotels booked
        //String SELECT_BOOKINGS_BY_HOTEL_ROOM =  "SELECT * FROM booking WHERE hotel.hotel_id = booking.hotel_id AND room.room_number = booking.room_number";

        // get all bookings that are in the future
        //String FUTURE_BOOKINGS_SQL = "SELECT * FROM booking WHERE check_in_date > TODAY AND check_out_date > TODAY";
        //SELECT COUNT(HOTEL_ID) FORM booking WHERE

        String sql = STR."SELECT COUNT(*) FROM booking WHERE (booking.hotel_id =  \{hotel.getHotelId()} + AND booking.room_number = \{room.getRoomNumber()}) AND EXISTS(SELECT * FROM booking WHERE check_in_date > \{today} AND check_out_date > \{today})";

//        return database.execute(sql) != 0;

        //TODO: FIGURE OUT HOW TO RUN AGGREGATE QUERY.
        return false;
    }


}
