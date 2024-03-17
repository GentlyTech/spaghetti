package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainAddresses")
public class HotelChainAddress extends JsonSerializable {
    @ManyToOne
    @JoinColumn(name = "chainName")
    private HotelChain hotelChain;

    @EmbeddedId
    private Address address;

    protected HotelChainAddress() {

    }
}
