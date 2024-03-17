package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import jakarta.persistence.Embeddable;

@Embeddable
public class Role {
    private String roleName;
    
    private String description;
    
    protected Role() {
        
    }
}
