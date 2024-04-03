package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

import java.time.LocalDate;

public class BookingId {
    private int roomNumber = -1;
    private int customerId = -1;
    private int hotelId = -1;
    private String checkInDate = null;
    private String checkOutDate = null;

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

    public LocalDate getCheckInDate() {
        return LocalDate.parse(checkInDate);
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate.toString();
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(checkOutDate);
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate.toString();
    }
}
