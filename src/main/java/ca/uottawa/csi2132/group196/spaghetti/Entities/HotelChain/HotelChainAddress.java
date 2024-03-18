package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainAddresses")
public class HotelChainAddress {
    @ManyToOne
    @JoinColumn(name = "chainName")
    private HotelChain hotelChain;

    @EmbeddedId
    private Address address;

    protected HotelChainAddress() {

    }

    public HotelChain getHotelChain() {
        return this.hotelChain;
    }

    public Address getAddress() {
        return this.address;
    }
}
