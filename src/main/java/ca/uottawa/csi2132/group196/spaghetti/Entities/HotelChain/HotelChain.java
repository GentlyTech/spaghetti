package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class HotelChain extends JsonSerializable {
    @Id
    private String chainName;

    @OneToMany(mappedBy = "hotelChain")
    private List<HotelChainContact> contacts;

    @OneToMany(mappedBy = "hotelChain")
    private List<HotelChainAddress> addresses;

    @OneToMany(mappedBy = "owner")
    
    private List<Hotel> hotels;

    protected HotelChain() {

    }

    public HotelChain(String chainName) {
        this.chainName = chainName;
    }

    public String getChainName() {
        return this.chainName;
    }

}
