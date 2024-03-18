package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainAddresses")
public class HotelChainAddress {
    @DoNotSerialize
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
