package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.WeakEntities.AddressId;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelAddresses")
public class HotelAddress {
    @OneToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @EmbeddedId
    private AddressId address;

    protected HotelAddress() {

    }

}
