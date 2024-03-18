package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Entities.Booking;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer extends User {
    
    private String idType;
    
    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
    
    protected Customer() {
        
    }
    
    public String getIdType() {
        return this.idType;
    }
    
    public List<Booking> getBookings() {
        return this.bookings;
    }
}
