package ca.uottawa.csi2132.group196.spaghetti.Entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class HotelChain {
    @Id
    private String chainName;

    @ElementCollection
    private List<Contact> contacts;

    @ElementCollection
    private List<Address> addresses;

    protected HotelChain() {

    }

    public HotelChain(String chainName) {
        this.chainName = chainName;
    }

    public String getChainName() {
        return null;
    }

    public int getHotelCount() {
        return -1; // TODO implement this
    }
}
