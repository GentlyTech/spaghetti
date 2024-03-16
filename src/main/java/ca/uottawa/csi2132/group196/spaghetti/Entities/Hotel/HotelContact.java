package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain.HotelChain;
import ca.uottawa.csi2132.group196.spaghetti.WeakEntities.ContactId;
import jakarta.persistence.*;

@Entity
@Table(name = "HotelContacts")
public class HotelContact {
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;
    
    @EmbeddedId
    ContactId contact;
    
    protected HotelContact() {
        
    }
}
