package ca.uottawa.csi2132.group196.spaghetti.RestSchemas;

public class RoomQuery {
    private Double[] priceRange = null;

    private String chainName = null;

    private String checkInDate = null;

    private String checkOutDate = null;

    private String hotelName = null;

    private String location = null;

    private Integer rating = -1;

    private Integer capacity = -1;

    public Double[] getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Double[] priceRange) {
        this.priceRange = priceRange;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating == null) {
            this.rating = -1;
            return;
        }
        this.rating = rating;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        if (capacity == null) {
            this.capacity = -1;
            return;
        }
        this.capacity = capacity;
    }
}
