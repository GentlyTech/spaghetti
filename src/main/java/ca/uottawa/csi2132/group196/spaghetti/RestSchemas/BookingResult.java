package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Booking;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Customer;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;

public class BookingResult {
//    private Booking booking;
//    private Customer customer;
//    private Room room;
//    public Booking getBooking() {
//        return booking;
//    }
//    public void setBooking(Booking booking) {
//        this.booking = booking;
//    }
//    public Customer getCustomer() {
//        return customer;
//    }
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//    public Room getRoom() {
//        return room;
//    }
//    public void setRoom(Room room) {
//        this.room = room;
//    }

    @MappedField("check_in_date")
    @Autofill
    private String checkInDate;

    @MappedField("check_out_date")
    @Autofill
    private String checkOutDate;
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
    @MappedField("damage_fee")
    @Autofill
    private String damageFee;

    @MappedField("id_type")
    @Autofill
    private String idType;

    @MappedField("name")
    @Autofill
    private String customerName;
    @MappedField("price")
    @Autofill
    private double price;
    @MappedField("view_type")
    @Autofill
    private String viewType;
    @MappedField("capacity")
    @Autofill
    private int capacity;
    @MappedField("extendable")
    @Autofill
    private boolean extendable;

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

    public String getDamageFee() {
        return damageFee;
    }

    public void setDamageFee(String damageFee) {
        this.damageFee = damageFee;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }
}
