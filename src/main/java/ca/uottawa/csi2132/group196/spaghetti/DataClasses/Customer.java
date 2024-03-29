package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Types.IDType;

import java.time.LocalDateTime;

public class Customer {
    @MappedField("customer_id")
    private int customerId = -1;

    @MappedField("creation_date")
    private LocalDateTime creationDate = null;

    @MappedField("id_type")
    private IDType idType = IDType.NONE;

    @MappedField
    private String name = null;

    @MappedField
    private Address address = null;

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
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public IDType getIdType() {
        return idType;
    }

    public void setIdType(IDType idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
