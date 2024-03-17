package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.Entities.Room;
import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel extends JsonSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotelId;

    private String hotelName;

    private int rating;

    @OneToMany(mappedBy = "hotel")
    private List<HotelContact> contacts;

    @OneToMany(mappedBy = "owner")
    private List<Room> rooms;

    @OneToOne(mappedBy = "hotel")
    private HotelAddress address;

    @ManyToOne
    @JoinColumn(name = "owner")
    private HotelChain owner;

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
     *
     * @return the star rating of this hotel
     */
    public int getHotelRating() {
        return this.rating;
    }

    public HotelChain getHotelOwner() {
        return this.owner;
    }
}
