package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.WeakEntities.AddressId;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainAddresses")
public class HotelChainAddress {
    @ManyToOne
    @JoinColumn(name = "chainName")
    private HotelChain hotelChain;

    @EmbeddedId
    private AddressId address;

    protected HotelChainAddress() {

    }
}
