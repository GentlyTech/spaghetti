package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;

public class BookingResult {
    private Booking booking;
    private Customer customer;
    private Room room;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
