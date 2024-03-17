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
}
