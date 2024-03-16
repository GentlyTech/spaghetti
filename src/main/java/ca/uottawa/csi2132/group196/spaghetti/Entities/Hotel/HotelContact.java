package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.WeakEntities.Contact;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelContacts")
public class HotelContact {
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;
    
    @EmbeddedId
    Contact contact;
    
    protected HotelContact() {
        
    }
}
