package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Entities.Booking;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Customer extends User {
    private String idType;

    @DoNotSerialize
    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    @OneToOne(mappedBy = "customer")
    private CustomerAddress address;

    protected Customer() {

    }

    public String getIdType() {
        return this.idType;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }
}
