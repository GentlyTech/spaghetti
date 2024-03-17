package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Contact;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainContacts")
public class HotelChainContact {
    @ManyToOne
    @JoinColumn(name = "chainName")
    HotelChain hotelChain;
    
    @EmbeddedId
    Contact contact;
    
    protected HotelChainContact() {
        
    }
}
