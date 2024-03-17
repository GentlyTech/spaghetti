package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelAddresses")
public class HotelAddress extends JsonSerializable {
    @OneToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @EmbeddedId
    private Address address;

    protected HotelAddress() {

    }

}
