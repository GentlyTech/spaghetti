package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Amenity extends JsonSerializable implements Serializable {
    private String name;
    
    private String description;

    protected Amenity() {

    }
}
