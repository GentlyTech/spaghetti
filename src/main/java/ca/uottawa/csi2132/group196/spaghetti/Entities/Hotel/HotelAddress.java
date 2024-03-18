package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelAddresses")
public class HotelAddress {
    @DoNotSerialize
    @OneToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @EmbeddedId
    private Address address;

    protected HotelAddress() {

    }
    
    public Hotel getHotel() {
        return this.hotel;
    }
    
    public Address getAddress() {
        return this.address;
    }

}
