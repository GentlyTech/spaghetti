package ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Contact;
import ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel.Hotel;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelChainContacts")
public class HotelChainContact {
    @DoNotSerialize
    @ManyToOne
    @JoinColumn(name = "chainName")
    HotelChain hotelChain;

    @EmbeddedId
    Contact contact;

    protected HotelChainContact() {

    }

    public HotelChain getHotelChain() {
        return this.hotelChain;
    }

    public Contact getContact() {
        return this.contact;
    }
}
