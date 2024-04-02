package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Enums.BookingStatus;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BookingGenerator {
    private static final int MAX_DEFAULT_BOOKINGS = 20;
    private final Random random = new Random();

    private final List<Customer> customers;
    private final List<Room> rooms;

    public BookingGenerator(List<Customer> customers, List<Room> rooms) {
        this.customers = customers;
        this.rooms = rooms;
    }

    public List<Booking> generateBookings(int numBookings) {
        List<Booking> bookings = new LinkedList<>();

        for (Customer customer : customers) {
            int numBookingsForCustomer = random.nextInt(numBookings);
            for (int i = 0; i < numBookingsForCustomer; i++) {
                Room room = rooms.get(random.nextInt(rooms.size()));
                Booking booking = new Booking();

                booking.setRoomNumber(room.getRoomNumber());
                booking.setCustomerId(customer.getCustomerId());
                booking.setHotelId(room.getHotelId());

                LocalDate checkInDate = LocalDate.now().plusDays(random.nextInt(-365, 366));
                LocalDate checkOutDate = checkInDate.plusDays(random.nextInt(1, 32));
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);
                
                booking.setBookingStatus(BookingStatus.generateRandom().getValue());

                bookings.add(booking);
            }
        }
        return bookings;
    }

    public List<Booking> generateBookings() {
        return generateBookings(random.nextInt(1, MAX_DEFAULT_BOOKINGS));
    }
}
