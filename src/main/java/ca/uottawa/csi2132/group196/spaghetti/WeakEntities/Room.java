package ca.uottawa.csi2132.group196.spaghetti.WeakEntities;

import ca.uottawa.csi2132.group196.spaghetti.Entities.Hotel.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.Types.ViewType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @ManyToOne
    @JoinColumn(name = "owner")
    private Hotel owner;

    @Id
    private int roomNumber; // we will assume room numbers will be purely numeric

    private double price;

    private double damageFee;

    @ElementCollection
    @CollectionTable(
            joinColumns = {
                    @JoinColumn(name = "hotelChain"),
                    @JoinColumn(name = "roomNumber")
            }
    )
    private List<Problem> problems;

    private String occupancyType;

    private boolean extendable;

    private ViewType viewType;

    protected Room() {

    }
}
