package ca.uottawa.csi2132.group196.spaghetti.Entities;

import com.google.gson.Gson;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class HotelChain {
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
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
