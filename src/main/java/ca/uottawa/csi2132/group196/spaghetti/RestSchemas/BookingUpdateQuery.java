package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;

public class BookingUpdateQuery {
    BookingId bookingId = null;
    Booking updatedBooking = null;

    public BookingId getBookingId() {
        return bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public Booking getUpdatedBooking() {
        return updatedBooking;
    }

    public void setUpdatedBooking(Booking updatedBooking) {
        this.updatedBooking = updatedBooking;
    }

}
