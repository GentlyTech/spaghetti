package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee extends User {
    private String sin;
    
    @ElementCollection
    @CollectionTable(joinColumns = {
            @JoinColumn(name = "id")
    })
    private List<Role> roles;
    
    protected Employee() {
        
    }
    
    public String getSin() {
        return this.sin;
    }
    
    public List<Role> getRoles() {
        return this.roles;
    }
}
