package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;

import java.time.LocalDateTime;
import java.util.List;

public class Customer {
    @MappedField("customer_id")
    private int customerId = -1;

    @MappedField("creation_date")
    private String creationDate = null;

    @MappedField("id_type")
    private String idType = null;

    @MappedField
    private String name = null;

    @MappedField
    private Address address = null;

    private List<Booking> bookings = null;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreationDate() {
        return LocalDateTime.parse(creationDate);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate.toString();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
