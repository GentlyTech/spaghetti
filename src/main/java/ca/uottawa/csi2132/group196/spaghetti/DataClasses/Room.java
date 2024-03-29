package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Types.ViewType;

import java.util.List;

public class Room {
    @MappedField("hotel_id")
    private int hotelId = -1;

    @MappedField("room_number")
    private int roomNumber = -1;

    @MappedField
    private double price = 0.0;

    @MappedField("damage_fee")
    private double damageFee = 0.0;

    @MappedField
    private boolean extendable = false;

    @MappedField("occupancy_type")
    private String occupancyType = null;

    @MappedField("view_type")
    private ViewType viewType = ViewType.BORING;

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

    public double getDamageFee() {
        return damageFee;
    }

    public void setDamageFee(double damageFee) {
        this.damageFee = damageFee;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public String getOccupancyType() {
        return occupancyType;
    }

    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }
}
