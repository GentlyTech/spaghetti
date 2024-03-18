package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import jakarta.persistence.*;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    private String creationDate;

    protected User() {
        
    }
    
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCreationDate() {
        return this.creationDate;
    }
}
