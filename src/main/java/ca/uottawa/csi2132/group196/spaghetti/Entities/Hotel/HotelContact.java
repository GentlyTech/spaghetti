package ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Contact;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "hotelContacts")
public class HotelContact {
    @DoNotSerialize
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;

    @EmbeddedId
    Contact contact;

    protected HotelContact() {

    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public Contact getContact() {
        return this.contact;
    }
}
