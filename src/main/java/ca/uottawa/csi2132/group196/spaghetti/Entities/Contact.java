package ca.uottawa.csi2132.group196.spaghetti.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {
    private String name;
    private String email;
    private String phoneNumber;
}
