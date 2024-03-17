package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "customerAddresses")
public class CustomerAddress {
    @OneToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @EmbeddedId
    private Address address;

    protected CustomerAddress() {

    }

}