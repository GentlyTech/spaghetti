package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.WeakEntities.ContactId;
import jakarta.persistence.*;

@Entity
@Table(name = "HotelChainContacts")
public class HotelChainContact {
    @ManyToOne
    @JoinColumn(name = "chainName")
    HotelChain hotelChain;
    
    @EmbeddedId
    ContactId contact;
    
    protected HotelChainContact() {
        
    }
}
