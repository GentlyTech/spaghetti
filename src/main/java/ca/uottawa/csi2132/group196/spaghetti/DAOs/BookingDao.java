package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingDao {
    private static final String SELECT_BOOKING_BY_HOTEL_ROOM_SQL = "SELECT * FROM booking WHERE room_id = ? AND hotel_id = ?";
    private static final String SELECT_BOOKINGS_BY_CUSTOMER_SQL = "SELECT * FROM booking WHERE customer_id = ?";
    private static final String SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL = "SELECT * FROM booking WHERE customer_id = ? AND hotel_id = ?";
    private static final String INSERT_BOOKING_SQL = "INSERT INTO booking (room_number, customer_id, hotel_id, booking_status, check_in_date, check_out_date, damage_fee) VALUES (?, ?, ?, ?, ? , ?, ?)";
    private static final String UPDATE_BOOKING_STATUS_SQL = "UPDATE booking SET booking_status = ? WHERE hotel_id = ? AND customer_id = ? AND room_number = ?";
    private static final String SELECT_BOOKINGS_BY_HOTEL_ROOM = "SELECT * FROM booking WHERE booking.hotel_id = ? AND booking.room_number = ?";
    private static final String BOOKING_EXISTS_COUNT_SQL = "SELECT COUNT(*) FROM booking WHERE (booking.hotel_id = ? AND booking.room_number = ?) AND EXISTS(SELECT * FROM booking WHERE check_in_date > ? AND check_out_date > ?)";
    private final JdbcTemplate database;


    public BookingDao(JdbcTemplate database) {
        this.database = database;
    }


    public void insertBooking(Booking booking) {
        database.update(INSERT_BOOKING_SQL, booking.getRoomNumber(), booking.getCustomerId(), booking.getHotelId(), booking.getBookingStatus(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getDamageFee());
    }

    public void insertBookings(List<Booking> bookings) {
        List<Object[]> batch = new ArrayList<>(bookings.size());
        for (Booking booking : bookings) {
            Object[] values = new Object[]{
                    booking.getRoomNumber(), booking.getCustomerId(), booking.getHotelId(), booking.getBookingStatus(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getDamageFee()
            };
            batch.add(values);
        }
        database.batchUpdate(INSERT_BOOKING_SQL, batch);
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKINGS_BY_CUSTOMER_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.execute(customer.getCustomerId());
    }

    public List<Booking> getBookingsByCustomerAndHotel(Customer customer, Hotel hotel) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        mapper.declareParameter(new SqlParameter(Types.INTEGER, "hotel_id"));
        return mapper.execute(customer.getCustomerId(), hotel.getHotelId());
    }

    public void updateStatus(Booking booking, String newStatus) {
        database.update(UPDATE_BOOKING_STATUS_SQL, newStatus, booking.getHotelId(), booking.getCustomerId(), booking.getCustomerId());
    }

    public boolean isBooked(LocalDate checkInDate, LocalDate checkOutDate, Room room) {
        Integer bookingCount = database.queryForObject(BOOKING_EXISTS_COUNT_SQL, Integer.class, room.getHotelId(), room.getRoomNumber(), checkInDate, checkOutDate);
        if (bookingCount == null) return false;
        return bookingCount > 0;
    }

}
