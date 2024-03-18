package ca.uottawa.csi2132.group196.spaghetti.Entities;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Problem;
import ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Types.ViewType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel owner;

    @Id
    private int roomNumber; // we will assume room numbers will be purely numeric

    private double price;

    private double damageFee;

    @ElementCollection
    @CollectionTable(
            joinColumns = {
                    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId"),
                    @JoinColumn(name = "roomNumber", referencedColumnName = "roomNumber")
            }
    )
    private List<Problem> problems;
    
    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    private String occupancyType;

    private boolean extendable;

    private ViewType viewType;

    @ElementCollection
    @CollectionTable(
            joinColumns = {
                    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId"),
                    @JoinColumn(name = "roomNumber", referencedColumnName = "roomNumber")
            }
    )
    private List<Amenity> amenities;

    protected Room() {

    }
    
    public Hotel getOwner() {
        return this.owner;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDamageFee() {
        return this.damageFee;
    }

    public List<Problem> getProblems() {
        return this.problems;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public String getOccupancyType() {
        return this.occupancyType;
    }

    public boolean isExtendable() {
        return this.extendable;
    }

    public ViewType getViewType() {
        return this.viewType;
    }
}
