package ca.uottawa.csi2132.group196.spaghetti.DAOs;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.*;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.BookingResult;
import ca.uottawa.csi2132.group196.spaghetti.RestSchemas.RoomQueryResult;
import ca.uottawa.csi2132.group196.spaghetti.Utils.FieldMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.rmi.MarshalledObject;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

@Repository
public class BookingDao {
    private static final String SELECT_BOOKING_BY_HOTEL_ROOM_SQL = "SELECT * FROM booking WHERE room_id = ? AND hotel_id = ?";
    private static final String SELECT_BOOKINGS_BY_CUSTOMER_SQL = "SELECT * FROM booking WHERE customer_id = ?";
    private static final String SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL = "SELECT * FROM booking WHERE customer_id = ? AND hotel_id = ?";
    private static final String INSERT_BOOKING_SQL = "INSERT INTO booking (room_number, customer_id, hotel_id, booking_status, check_in_date, check_out_date, damage_fee) VALUES (?, ?, ?, ?, ? , ?, ?)";
    private static final String SELECT_BOOKING_SQL = "SELECT * FROM booking WHERE room_number = ? AND hotel_id = ? AND check_in_date = ? AND check_out_date = ?";
    private static final String DELETE_BOOKING_SQL = "DELETE FROM booking WHERE room_number = ? AND hotel_id = ? AND check_in_date = ? AND check_out_date = ?";
    private static final String UPDATE_BOOKING_SQL = "UPDATE booking SET room_number = ?, customer_id = ?, hotel_id = ?, booking_status = ?, check_in_date = ?, check_out_date = ?, damage_fee = ? WHERE room_number = ? AND hotel_id = ? AND check_in_date = ? AND check_out_date = ?";
    private static final String SELECT_BOOKINGS_BY_HOTEL_ROOM_SQL = "SELECT * FROM booking WHERE booking.hotel_id = ? AND booking.room_number = ?";
    private static final String SELECT_BOOKINGS_BY_HOTEL_SQL = "SELECT * FROM booking WHERE booking.hotel_id = ?";
    private static final String SELECT_DETAILED_BOOKING_BY_HOTEL_SQL = "SELECT * FROM booking JOIN public.customer on booking.customer_id = customer.customer_id JOIN room on booking.hotel_id = :hotelId AND room.hotel_id = :hotelId AND booking.room_number = room.room_number";
    private static final String BOOKING_EXISTS_COUNT_SQL = "SELECT COUNT(*) FROM booking WHERE (booking.hotel_id = ? AND booking.room_number = ?) AND EXISTS(SELECT * FROM booking WHERE check_in_date > ? AND check_out_date > ?)";
    private final JdbcTemplate database;
    private final NamedParameterJdbcTemplate namedDatabase;


    public BookingDao(DataSource dataSource) {
        this.database = new JdbcTemplate(dataSource);
        this.namedDatabase = new NamedParameterJdbcTemplate(dataSource);
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

    public List<Booking> getBookingsByCustomerId(int customerId) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKINGS_BY_CUSTOMER_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        return mapper.execute(customerId);
    }

    public List<Booking> getBookingsByCustomerAndHotel(Customer customer, Hotel hotel) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKING_BY_CUSTOMER_HOTEL_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "customer_id"));
        mapper.declareParameter(new SqlParameter(Types.INTEGER, "hotel_id"));
        return mapper.execute(customer.getCustomerId(), hotel.getHotelId());
    }

    public Booking getBookingById(int roomNumber, int hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKING_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "room_number"));
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        mapper.declareParameter(new SqlParameterValue(Types.DATE, "check_in_date"));
        mapper.declareParameter(new SqlParameterValue(Types.DATE, "check_out_date"));
        return mapper.findObject(roomNumber, hotelId, checkInDate, checkOutDate);
    }

    public void updateBooking(int roomNumber, int hotelId, LocalDate checkInDate, LocalDate checkOutDate, Booking booking) {
        if (booking == null) return;
        Booking previousBooking = getBookingById(roomNumber, hotelId, checkInDate, checkOutDate);
        if (previousBooking == null) return;

        booking.fillFromInstance(previousBooking);
        database.update(UPDATE_BOOKING_SQL, booking.getRoomNumber(), booking.getCustomerId(), booking.getHotelId(), booking.getBookingStatus(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getDamageFee(), roomNumber, hotelId, checkInDate, checkOutDate);
    }

    public void deleteBooking(int roomNumber, int hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        database.update(DELETE_BOOKING_SQL, roomNumber, hotelId, checkInDate, checkOutDate);
    }

    public boolean isBooked(LocalDate checkInDate, LocalDate checkOutDate, Room room) {
        Integer bookingCount = database.queryForObject(BOOKING_EXISTS_COUNT_SQL, Integer.class, room.getHotelId(), room.getRoomNumber(), checkInDate, checkOutDate);
        if (bookingCount == null) return false;
        return bookingCount > 0;
    }

    public List<Booking> getBookingsByHotel(int hotel_id) {
        FieldMapper<Booking> mapper = new FieldMapper<>(database.getDataSource(), SELECT_BOOKINGS_BY_HOTEL_SQL, Booking.class);
        mapper.declareParameter(new SqlParameterValue(Types.INTEGER, "hotel_id"));
        return mapper.execute(hotel_id);
    }

    public List<BookingResult> getDetailedBookingsByHotel(int hotelId) {
        Map<String, Object> params = new HashMap<>();
        params.put("hotelId", hotelId);
        
        return namedDatabase.query(SELECT_DETAILED_BOOKING_BY_HOTEL_SQL, params, (resultSet, rowNum) -> {
            BookingResult result = new BookingResult();

            Room room = new Room();
            room.setHotelId(resultSet.getInt("hotel_id"));
            room.setRoomNumber(resultSet.getInt("room_number"));
            room.setCapacity(resultSet.getInt("capacity"));
            room.setPrice(resultSet.getDouble("price"));
            room.setExtendable(resultSet.getBoolean("extendable"));
            room.setViewType(resultSet.getString("view_type"));

            result.setRoom(room);

            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setIdType(resultSet.getString("id_type"));
            customer.setName(resultSet.getString("name"));
            customer.setCreationDate(resultSet.getString("creation_date"));

            result.setCustomer(customer);

            Booking booking = new Booking();
            booking.setRoomNumber(resultSet.getInt("room_number"));
            booking.setCustomerId(resultSet.getInt("customer_id"));
            booking.setHotelId(resultSet.getInt("hotel_id"));
            booking.setBookingStatus(resultSet.getString("booking_status"));
            booking.setCheckInDate(resultSet.getString("check_in_date"));
            booking.setCheckOutDate(resultSet.getString("check_out_date"));
            booking.setDamageFee(resultSet.getInt("damage_fee"));
            
            result.setBooking(booking);
            return result;
        });
    }
}
