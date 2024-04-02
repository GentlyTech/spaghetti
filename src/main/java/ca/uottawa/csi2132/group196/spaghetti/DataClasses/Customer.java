package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.Autofill;
import ca.uottawa.csi2132.group196.spaghetti.Annotations.MappedField;
import ca.uottawa.csi2132.group196.spaghetti.Utils.AutoFillableClass;

import java.time.LocalDateTime;

public class Customer extends AutoFillableClass<Customer> {
    @MappedField("customer_id")
    @Autofill
    private int customerId = -1;

    @MappedField("creation_date")
    @Autofill
    private String creationDate = null;

    @MappedField("id_type")
    @Autofill
    private String idType = null;

    @MappedField
    @Autofill
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
