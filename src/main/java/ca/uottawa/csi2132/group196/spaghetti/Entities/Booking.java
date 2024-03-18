package ca.uottawa.csi2132.group196.spaghetti.Entities;

import ca.uottawa.csi2132.group196.spaghetti.Entities.User.Customer;
import ca.uottawa.csi2132.group196.spaghetti.Types.BookingStatus;
import jakarta.persistence.*;

@Entity
public class Booking {
    private BookingStatus status;

    private String checkInDate;

    private String checkOutDate;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hotelId", referencedColumnName = "hotelId"),
            @JoinColumn(name = "roomNumber", referencedColumnName = "roomNumber")
    })
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
    
    protected Booking() {
        
    }
    
    public BookingStatus getBookingStatus() {
        return this.status;
    }
    
    public String getCheckInDate() {
        return this.checkInDate;
    }
    
    public String getCheckOutDate() {
        return this.checkOutDate;
    }
}
