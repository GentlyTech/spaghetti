package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Contact;
import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelContacts")
public class HotelContact extends JsonSerializable {
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;
    
    @EmbeddedId
    Contact contact;
    
    protected HotelContact() {
        
    }
}
