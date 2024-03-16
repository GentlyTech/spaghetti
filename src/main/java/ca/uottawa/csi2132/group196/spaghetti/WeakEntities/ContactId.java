package ca.uottawa.csi2132.group196.spaghetti.WeakEntities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ContactId implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;
    
    protected ContactId() {
        
    }
    
    public ContactId(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
