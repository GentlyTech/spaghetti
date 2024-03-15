package ca.uottawa.csi2132.group196.spaghetti.Entities;

import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel extends JsonSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotelId;
    
    private String hotelName;
    
    private int rating;
    
    protected Hotel() {
        
    }
    
    public Hotel(long hotelId, String hotelName, int rating) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.rating = rating;
    }

    public long getHotelId() {
        return this.hotelId;
    }
    
    public String getHotelName() {
        return this.hotelName;
    }

    /**
     * Rating as in the "star" classification of the hotel, not the customer review rating.
     * @return the star rating of this hotel
     */
    public int getHotelRating() {
        return this.rating;
    }
}
