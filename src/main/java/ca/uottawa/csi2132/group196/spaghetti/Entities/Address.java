package ca.uottawa.csi2132.group196.spaghetti.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    
}
