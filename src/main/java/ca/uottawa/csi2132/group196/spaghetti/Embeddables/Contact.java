package ca.uottawa.csi2132.group196.spaghetti.Embeddables;

import ca.uottawa.csi2132.group196.spaghetti.Types.JsonSerializable;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Contact extends JsonSerializable implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;

    protected Contact() {

    }

    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
