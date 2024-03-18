package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Annotations.DoNotSerialize;
import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "customerAddresses")
public class CustomerAddress {
    @DoNotSerialize
    @OneToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @EmbeddedId
    private Address address;

    protected CustomerAddress() {

    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Address getAddress() {
        return this.address;
    }
}