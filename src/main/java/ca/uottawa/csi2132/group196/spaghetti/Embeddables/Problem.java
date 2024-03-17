package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class Problem extends JsonSerializable {
    private String description;
    
    protected Problem() {
        
    }
    
    public Problem(String description) {
        this.description = description;
    }
}
