package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Amenity implements Serializable {
    @Column(nullable = false)
    private String name;
    
    private String description;

    protected Amenity() {

    }
    
    public Amenity(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
}
