package ca.uottawa.csi2132.group196.spaghetti.Entities;

import ca.uottawa.csi2132.group196.spaghetti.Entities.IdClasses.AddressId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Address {
    @EmbeddedId
    private AddressId addressId;
    
    public Address() {
        
    }
    
}
