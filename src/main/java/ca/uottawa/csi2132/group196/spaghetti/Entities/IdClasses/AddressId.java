package ca.uottawa.csi2132.group196.spaghetti.Entities.IdClasses;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AddressId implements Serializable {
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    
    public AddressId() {
        
    }
    
    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCountry() {
        return this.country;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
}
