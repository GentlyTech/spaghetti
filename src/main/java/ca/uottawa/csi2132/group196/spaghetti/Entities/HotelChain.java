package ca.uottawa.csi2132.group196.spaghetti.Entities;

import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class HotelChain extends JsonSerializable {
    @Id
    private String chainName;

    @ElementCollection
    @CollectionTable(
            joinColumns = @JoinColumn(name = "chainName")
    )
    private List<Contact> contacts;

    @ElementCollection
    @CollectionTable(
            joinColumns = @JoinColumn(name = "chainName")
    )
    private List<Address> addresses;

    protected HotelChain() {

    }

    public HotelChain(String chainName) {
        this.chainName = chainName;
    }

    public String getChainName() {
        return this.chainName;
    }

}
