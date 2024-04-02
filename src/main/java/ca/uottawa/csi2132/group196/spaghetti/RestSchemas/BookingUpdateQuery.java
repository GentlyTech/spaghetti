package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;

public class BookingUpdateQuery {

    Booking updatedBooking = null;
    private int roomNumber = -1;
    private int customerId = -1;
    private int hotelId = -1;
    private String checkInDate = null;
    private String checkOutDate = null;

    public Booking getUpdatedBooking() {
        return updatedBooking;
    }

    public void setUpdatedBooking(Booking updatedBooking) {
        this.updatedBooking = updatedBooking;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
