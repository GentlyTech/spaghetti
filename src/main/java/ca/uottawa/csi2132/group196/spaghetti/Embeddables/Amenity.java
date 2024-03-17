package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Amenity implements Serializable {
    private String name;
    
    private String description;

    protected Amenity() {

    }
}
