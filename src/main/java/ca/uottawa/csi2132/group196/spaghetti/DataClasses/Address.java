package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;

public class Address {
    @MappedField("address_id")
    private int addressId = -1;

    @MappedField
    private String alias = null;

    @MappedField
    private String city = null;

    @MappedField
    private String country = null;

    @MappedField
    private String postalCode = null;

    @MappedField
    private String province = null;

    @MappedField
    private String street = null;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
