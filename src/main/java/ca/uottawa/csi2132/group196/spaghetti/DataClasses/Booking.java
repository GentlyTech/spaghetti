package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Utils.AutoFillableClass;

import java.time.LocalDate;

public class Booking extends AutoFillableClass<Booking> {
    @MappedField("room_number")
    @Autofill
    private int roomNumber;

    @MappedField("customer_id")
    @Autofill
    private int customerId;

    @MappedField("hotel_id")
    @Autofill
    private int hotelId;

    @MappedField("booking_status")
    @Autofill
    private String bookingStatus;

    @MappedField("check_in_date")
    @Autofill
    private String checkInDate;

    @MappedField("check_out_date")
    @Autofill
    private String checkOutDate;
    @MappedField("damage_fee")
    @Autofill
    private double damageFee;

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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDate getCheckInDate() {
        return LocalDate.parse(this.checkInDate);
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate.toString();
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(this.checkOutDate);
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate.toString();
    }

    public double getDamageFee() {
        return damageFee;
    }

    public void setDamageFee(double damageFee) {
        this.damageFee = damageFee;
    }
}
