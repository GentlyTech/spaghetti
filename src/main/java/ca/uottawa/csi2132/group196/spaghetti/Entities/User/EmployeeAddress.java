package ca.uottawa.csi2132.group196.spaghetti.Entities.User;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "employeeAddresses")
public class EmployeeAddress {
    @OneToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;

    @EmbeddedId
    private Address address;

    protected EmployeeAddress() {

    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Address getAddress() {
        return this.address;
    }

}