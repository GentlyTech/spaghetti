package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;

import java.util.List;


public class Room {
    @MappedField("hotel_id")
    private int hotelId = -1;

    @MappedField("room_number")
    private int roomNumber = -1;

    @MappedField
    private double price = 0.0;

    @MappedField
    private boolean extendable = false;

    @MappedField
    private int capacity = 0;

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

    public String getCapacity() {
        return occupancyType;
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
