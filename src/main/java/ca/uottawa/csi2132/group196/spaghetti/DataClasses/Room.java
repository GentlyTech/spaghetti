package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Utils.AutoFillableClass;

import java.util.List;


public class Room extends AutoFillableClass<Room> {
    @Autofill
    @MappedField("hotel_id")
    private int hotelId = -1;

    @Autofill
    @MappedField("room_number")
    private int roomNumber = -1;

    @Autofill
    @MappedField
    private double price = 0.0;

    @Autofill
    @MappedField
    private boolean extendable = false;

    @Autofill
    @MappedField
    private int capacity = -1;

    @Autofill
    @MappedField("view_type")
    private String viewType = null;

    private List<Amenity> amenities = null;

    private List<Problem> problems = null;

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
