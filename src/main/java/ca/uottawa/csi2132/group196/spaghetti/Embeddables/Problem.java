package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Problem {
    private String description;
    
    protected Problem() {
        
    }
    
    public Problem(String description) {
        this.description = description;
    }
}
